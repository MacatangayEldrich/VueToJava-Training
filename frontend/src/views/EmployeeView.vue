<script setup lang="ts">
import { computed, ref } from 'vue'
import ActualOvertimeForm from '../components/ActualOvertimeForm.vue'
import OvertimeList from '../components/OvertimeList.vue'
import PlannedOvertimeDetails from '../components/PlannedOvertimeDetails.vue'
import PlannedOvertimeForm from '../components/PlannedOvertimeForm.vue'

interface PlannedOvertime {
  id: number
  employeeId: number
  workdayId: number
  date: string
  hours: number
  activity: string
  otDescription: string
  plannedHours: number
}

interface PlannedOvertimeInput {
  date: string
  hours: number
  activity: string
}

interface OvertimeEntry {
  id: number
  dateEarned: string
  factorRate: number
  overtimeHours: number
  ctoHours: number
  expirationDate: string
}

function localDateValue(date: Date) {
  const year = date.getFullYear()
  const month = String(date.getMonth() + 1).padStart(2, '0')
  const day = String(date.getDate()).padStart(2, '0')
  return `${year}-${month}-${day}`
}

const today = localDateValue(new Date())
const nextId = ref(1)
const plannedOvertime = ref<PlannedOvertime[]>([])
const overtimeEntries = ref<OvertimeEntry[]>([])

const availableBalance = computed(() => overtimeEntries.value
  .filter((entry) => entry.expirationDate >= today)
  .reduce((total, entry) => total + entry.ctoHours, 0))

function expirationDate(dateValue: string) {
  const [year, month, day] = dateValue.split('-').map(Number)
  const expiry = new Date(year, month - 1, day)
  expiry.setFullYear(expiry.getFullYear() + 1)
  if (expiry.getMonth() !== month - 1) {
    expiry.setDate(0)
  }
  return localDateValue(expiry)
}

function submitPlan(plan: PlannedOvertimeInput) {
  const id = nextId.value++
  plannedOvertime.value.unshift({
    id,
    ...plan,
    employeeId: 0,
    workdayId: id,
    otDescription: plan.activity,
    plannedHours: plan.hours,
  })
}

function submitActual(entry: Omit<OvertimeEntry, 'id' | 'ctoHours' | 'expirationDate'>) {
  const ctoHours = Math.round(entry.overtimeHours * entry.factorRate * 100) / 100
  overtimeEntries.value.unshift({
    id: nextId.value++,
    ...entry,
    ctoHours,
    expirationDate: expirationDate(entry.dateEarned),
  })
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
      <PlannedOvertimeForm :today="today" @submit="submitPlan" />
      <ActualOvertimeForm :today="today" @submit="submitActual" />
    </div>

    <PlannedOvertimeDetails :plans="plannedOvertime" />
    <OvertimeList :entries="overtimeEntries" :balance="availableBalance" />
  </section>
</template>