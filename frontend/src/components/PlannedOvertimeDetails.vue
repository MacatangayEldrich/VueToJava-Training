<script setup lang="ts">
interface PlannedOvertime {
  id: number
  employeeId: number
  workdayId: number
  otDescription: string
  plannedHours: number
  date: string
  hours: number
  activity: string
}

defineProps<{ plans: PlannedOvertime[] }>()

function formatDate(dateValue: string) {
  return new Date(`${dateValue}T00:00:00`).toLocaleDateString(undefined, {
    year: 'numeric',
    month: 'short',
    day: 'numeric',
  })
}
</script>

<template>
  <section class="content-section plans-section" aria-labelledby="plans-title">
    <div class="section-heading">
      <div><p class="eyebrow">Coming up</p><h2 id="plans-title">Planned overtime</h2></div>
      <span class="count-badge">{{ String(plans.length).padStart(2, '0') }}</span>
    </div>
    <div v-if="plans.length" class="plan-list">
      <article v-for="plan in plans" :key="plan.id" class="plan-item">
        <time>{{ formatDate(plan.date) }}</time>
        <strong>{{ plan.activity }}</strong>
        <span>{{ plan.hours }} planned hours</span>
      </article>
    </div>
    <p v-else class="empty-state">No overtime planned yet. Add a plan above to keep track of upcoming work.</p>
  </section>
</template>