<script setup lang="ts">
import { ref, watch } from 'vue'
import type { PlannedOvertime } from '../models/overtime'

interface PlannedOvertimePage {
  content: PlannedOvertime[]
  number: number
  totalPages: number
  totalElements: number
}

const props = defineProps<{ employeeId: number; refreshKey: number }>()
const plans = ref<PlannedOvertime[]>([])
const currentPage = ref(0)
const totalPages = ref(0)
const totalElements = ref(0)
const loading = ref(false)
const errorMessage = ref('')
const pageSize = 5

watch(
  () => [props.employeeId, props.refreshKey, currentPage.value] as const,
  async ([employeeId, refreshKey, page], previous, onCleanup) => {
    if (
      previous &&
      (employeeId !== previous[0] || refreshKey !== previous[1]) &&
      page !== 0
    ) {
      currentPage.value = 0
      return
    }

    const controller = new AbortController()
    onCleanup(() => controller.abort())
    loading.value = true
    errorMessage.value = ''

    try {
      const response = await fetch(
        `/v1/planned-overtimes/employee/${employeeId}?page=${page}&size=${pageSize}`,
        {
          signal: controller.signal,
        },
      )
      if (!response.ok) {
        throw new Error(`Unable to load planned overtime (${response.status})`)
      }

      const result = await response.json() as PlannedOvertimePage
      plans.value = result.content
      currentPage.value = result.number
      totalPages.value = result.totalPages
      totalElements.value = result.totalElements
    } catch (error) {
      if (controller.signal.aborted) return
      errorMessage.value = error instanceof Error ? error.message : 'Unable to load planned overtime.'
    } finally {
      if (!controller.signal.aborted) {
        loading.value = false
      }
    }
  },
  { immediate: true },
)

function previousPage() {
  if (currentPage.value > 0) currentPage.value -= 1
}

function nextPage() {
  if (currentPage.value + 1 < totalPages.value) currentPage.value += 1
}

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
      <span class="count-badge">{{ String(totalElements).padStart(2, '0') }}</span>
    </div>
    <p v-if="loading" class="empty-state" role="status">Loading planned overtime…</p>
    <p v-else-if="errorMessage" class="empty-state" role="alert">{{ errorMessage }}</p>
    <div v-else-if="plans.length" class="plan-list">
      <div class="plan-header" style="font-size: 1rem">
        <strong>Planned Date</strong>
        <strong>Activity</strong>
        <strong>Planned Hour/s</strong>
      </div>
      <article v-for="plan in plans" :key="plan.poId" class="plan-item">
        <time>{{ formatDate(plan.plannedDate) }}</time>
        <strong>{{ plan.activity }}</strong>
        <span>{{ plan.plannedHours }}</span>
      </article>
    </div>
    <p v-else class="empty-state">No overtime planned yet. Add a plan above to keep track of upcoming work.</p>
    <nav
      v-if="!loading && !errorMessage && totalPages > 1"
      class="plans-pagination"
      aria-label="Planned overtime pages"
    >
      <button type="button" :disabled="currentPage === 0" @click="previousPage">Previous</button>
      <span aria-live="polite">Page {{ currentPage + 1 }} of {{ totalPages }}</span>
      <button type="button" :disabled="currentPage + 1 >= totalPages" @click="nextPage">Next</button>
    </nav>
  </section>
</template>

<style scoped>
.plans-pagination {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 12px;
  margin-top: 18px;
  color: var(--muted);
  font-size: 13px;
}

.plans-pagination button {
  padding: 8px 12px;
  border: 1px solid var(--line);
  background: var(--paper);
  color: var(--ink);
}

.plans-pagination button:disabled {
  cursor: not-allowed;
}
</style>