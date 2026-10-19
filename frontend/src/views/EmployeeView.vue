<script setup lang="ts">
import { computed, ref } from 'vue'
import OvertimeList from '../components/OvertimeList.vue'
import PlannedOvertimeDetails from '../components/PlannedOvertimeDetails.vue'
import PlannedOvertimeForm from '../components/PlannedOvertimeForm.vue'
import type { OvertimeEntry, PlannedOvertime } from '../models/overtime'

function localDateValue(date: Date) {
  const year = date.getFullYear()
  const month = String(date.getMonth() + 1).padStart(2, '0')
  const day = String(date.getDate()).padStart(2, '0')
  return `${year}-${month}-${day}`
}

const today = localDateValue(new Date())
const demoEmployeeId = 4
const plannedOvertime = ref<PlannedOvertime[]>([])
const overtimeEntries = ref<OvertimeEntry[]>([])

const availableBalance = computed(() => overtimeEntries.value
  .filter((entry) => entry.expirationDate >= today)
  .reduce((total, entry) => total + entry.ctoHours, 0))

function submitPlan(plan: PlannedOvertime) {
  plannedOvertime.value.unshift(plan)
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
      <div class="avatar" aria-label="Alex Morgan">AM</div>
    </div>

    <div class="overtime-forms">
      <PlannedOvertimeForm :today="today" :employee-id="demoEmployeeId" @submit="submitPlan" />

      <!-- <ActualOvertimeForm :today="today" @submit="submitActual" /> -->
    </div>

    
    <PlannedOvertimeDetails :plans="plannedOvertime" />
    <OvertimeList :entries="overtimeEntries" :balance="availableBalance" />
  </section>
</template>