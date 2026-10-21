<script setup lang="ts">
import { computed, ref, watch } from 'vue'
import PlannedOvertimeDetails from '../components/PlannedOvertimeDetails.vue'
import PlannedOvertimeForm from '../components/PlannedOvertimeForm.vue'
import type { OvertimeEntry } from '../models/overtime'
import { useMasterDataStore } from '../stores/masterDataStore'

function localDateValue(date: Date) {
  const year = date.getFullYear()
  const month = String(date.getMonth() + 1).padStart(2, '0')
  const day = String(date.getDate()).padStart(2, '0')
  return `${year}-${month}-${day}`
}

const today = localDateValue(new Date())
const props = defineProps<{ employeeId: number }>()
const masterDataStore = useMasterDataStore()
const employee = computed(() => masterDataStore.employees[props.employeeId])
const employeeLoading = ref(false)
const employeeError = ref<string | null>(null)
const showEmployeeDetails = ref(false)
const plannedOvertimeRefreshKey = ref(0)
const overtimeEntries = ref<OvertimeEntry[]>([])
const employeeInitials = computed(() => employee.value?.employeeName
  .split(/\s+/)
  .filter(Boolean)
  .map((name) => name[0])
  .join('')
  .slice(0, 2)
  .toUpperCase() ?? '—')

watch(() => props.employeeId, async (employeeId) => {
  employeeLoading.value = true
  employeeError.value = null
  try {
    await masterDataStore.loadEmployee(employeeId)
  } catch (cause) {
    employeeError.value = cause instanceof Error ? cause.message : 'Unable to load employee details.'
  } finally {
    employeeLoading.value = false
  }
}, { immediate: true })

const availableBalance = computed(() => overtimeEntries.value
  .filter((entry) => entry.expirationDate >= today)
  .reduce((total, entry) => total + entry.ctoHours, 0))

function submitPlan() {
  plannedOvertimeRefreshKey.value += 1
}
</script>

<template>
  <section class="route-view employee-view" aria-labelledby="employee-title">
    <div class="view-heading overtime-heading">
      <div>
        <p class="eyebrow">Employee space <span class="heading-divider">/</span> Overtime &amp; comp time</p>
        <h1 id="employee-title" class="page-title">Make time<br /><em>count.</em></h1>
        <p class="view-lede">Plan overtime, log what you worked, and keep your CTO balance in view.</p>
      </div>
      <button
        class="avatar"
        type="button"
        :title="employee?.employeeName"
        :aria-label="`Show employee details for ${employee?.employeeName ?? `employee ${props.employeeId}`}`"
        :aria-expanded="showEmployeeDetails"
        @click="showEmployeeDetails = true"
      >{{ employeeInitials }}</button>
    </div>

    <div v-if="showEmployeeDetails" class="employee-details-overlay" @click.self="showEmployeeDetails = false">
      <section
        class="employee-details-dialog content-section"
        role="dialog"
        aria-modal="true"
        aria-labelledby="employee-details-title"
      >
        <button class="employee-details-close" type="button" aria-label="Close employee details" @click="showEmployeeDetails = false">×</button>
        <div class="section-heading">
          <div><p class="eyebrow">Employee ID {{ props.employeeId }}</p><h2 id="employee-details-title">Your details</h2></div>
        </div>
        <p v-if="employeeLoading" class="form-hint" role="status">Loading employee details…</p>
        <p v-else-if="employeeError" class="form-hint" role="alert">{{ employeeError }}</p>
        <dl v-else-if="employee" class="employee-details-list">
          <div><dt>Name</dt><dd>{{ employee.employeeName }}</dd></div>
          <div><dt>Department</dt><dd>{{ employee.department }}</dd></div>
          <div><dt>Position</dt><dd>{{ employee.position }}</dd></div>
          <div><dt>Work days</dt><dd>{{ employee.workDays }}</dd></div>
          <div><dt>Work hours</dt><dd>{{ employee.workHours }}</dd></div>
        </dl>
      </section>
    </div>

    <div class="overtime-forms">
      <PlannedOvertimeForm :today="today" :employee-id="props.employeeId" @submit="submitPlan" />

      <!-- <ActualOvertimeForm :today="today" @submit="submitActual" /> -->
    </div>

    
    <PlannedOvertimeDetails
      :employee-id="props.employeeId"
      :refresh-key="plannedOvertimeRefreshKey"
    />
    <!-- <PlannedOvertimeListApproval :entries="overtimeEntries" :balance="availableBalance" />
    <ActualOvertimeListApproval :entries="overtimeEntries" :balance="availableBalance" /> -->
  </section>
</template>

<style scoped>
.employee-details-overlay {
  position: fixed;
  inset: 0;
  z-index: 9999;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
  background: rgba(23, 33, 31, 0.5);
}

.employee-details-dialog {
  position: relative;
  width: min(100%, 480px);
  max-height: 100%;
  overflow-y: auto;
  background: var(--paper);
}

.employee-details-close {
  position: absolute;
  top: 14px;
  right: 14px;
  width: 32px;
  height: 32px;
  border: 1px solid var(--line);
  background: var(--paper);
  color: var(--ink);
  font-size: 22px;
  line-height: 1;
}

.employee-details-list {
  display: grid;
  gap: 0;
  margin: 0;
}

.employee-details-list > div {
  display: flex;
  justify-content: space-between;
  gap: 16px;
  padding: 13px 0;
  border-bottom: 1px solid var(--line);
}

.employee-details-list > div:last-child {
  border-bottom: 0;
}

.employee-details-list dt {
  color: var(--muted);
}

.employee-details-list dd {
  margin: 0;
  font-weight: 700;
  text-align: right;
}
</style>