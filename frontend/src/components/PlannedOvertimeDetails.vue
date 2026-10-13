<script setup lang="ts">
interface PlannedOvertime {
  poId: number
  workdayId: number
  poDescription: string
  plannedHours: number
  filingDate: string
  plannedDate: string
}

defineProps<{ plans: PlannedOvertime[] }>()

function formatDate(dateValue: string) {
  return new Date(`${dateValue}T00:00:00`).toLocaleDateString(undefined, {
    year: 'numeric',
    month: 'short',
    day: 'numeric',
  })
}

function editPlannedOT(plan: PlannedOvertime) {
  alert('Edit button pressed')
  console.log('Edit planned OT', plan)
}

</script>

<template>
  <section class="content-section plans-section" aria-labelledby="plans-title">
    <div class="section-heading">
      <div><p class="eyebrow">Coming up</p><h2 id="plans-title">Planned overtime</h2></div>
      <span class="count-badge">{{ String(plans.length).padStart(2, '0') }}</span>
    </div>
    <div v-if="plans.length" class="plan-list">
      <article v-for="plan in plans" :key="plan.poId" class="plan-item">
        <time>{{ formatDate(plan.plannedDate) }}</time>
        <strong>{{ plan.poDescription }}</strong>
        <span>{{ plan.plannedHours }} Planned hours</span>
        <button type="button" @click="editPlannedOT(plan)" class="action-btn primary">
          <span>Edit</span>
        </button>
      </article>
    </div>
    <p v-else class="empty-state">No overtime planned yet. Add a plan above to keep track of upcoming work.</p>
  </section>
</template>
