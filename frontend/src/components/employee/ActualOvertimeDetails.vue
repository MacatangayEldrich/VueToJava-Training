<script setup lang="ts">
import { ref, watch } from 'vue'
import type { ActualOvertime } from '../../models/overtime.ts'
import { useOvertimeSyncStore } from '../../stores/overtimeSyncStore.ts'

interface ActualovertimePage {
  content: ActualOvertime[]
  number: number
  totalPages: number
  totalElements: number
}

const props = defineProps<{ employeeId: number }>()
const overtimeSyncStore = useOvertimeSyncStore()
const actualOvertimes = ref<ActualOvertime[]>([])
const currentPage = ref(0)
const totalPages = ref(0)
const totalElements = ref(0)
const loading = ref(false)
const errorMessage = ref('')
const pageSize = 5

watch(
    () => [props.employeeId, overtimeSyncStore.changeVersion, currentPage.value] as const,
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
        `/v1/actual-overtimes/employee/${employeeId}?page=${page}&size=${pageSize}`,
        {
          signal: controller.signal,
        },
      )
      if (!response.ok) {
        throw new Error(`Unable to load actual overtime (${response.status})`)
      }

      const result = await response.json() as ActualovertimePage
      actualOvertimes.value = result.content
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
	<section class="content-section ledger-section" aria-labelledby="ledger-title">
		<div class="section-heading">
			<div><p class="eyebrow">Your earned comp time</p><h2 id="ledger-title">Actual overtime</h2></div>
			<span class="ledger-note">Expired hours excluded from balance</span>
		</div>
		<div class="table-scroll">
			<table class="overtime-table">
				<thead>
					<tr>
						<th scope="col">Date earned</th>
						<th scope="col">Factor rate</th>
						<th scope="col">Rendered Hours</th>
						<th scope="col">Earned Hours earned</th>
						<th scope="col">Expiration date</th>
						<th scope="col">Status</th>
					</tr>
				</thead>
				<tbody v-if="actualOvertimes.length">
					<tr v-for="actualOvertime in actualOvertimes" :key="actualOvertime.aoId">
						<td>{{ formatDate(actualOvertime.actualDate) }}</td>
						<td>{{ actualOvertime.factorRate.toFixed(2) }}</td>
						<td>{{ actualOvertime.renderedHours.toFixed(2) }}</td>
						<td class="earned-hours">{{ actualOvertime.earnedHours.toFixed(2) }}</td>
						<td>{{ formatDate(actualOvertime.expirationDate) }}</td>
						<td>{{ actualOvertime.status }}</td>
					</tr>
				</tbody>
				<tbody v-else>
					<tr><td class="table-empty" colspan="5">No actual overtime recorded yet.</td></tr>
				</tbody>
                
			</table>
            <nav
                v-if="!loading && !errorMessage && totalPages > 1"
                class="plans-pagination"
                aria-label="Planned overtime pages"
                >
                <button type="button" :disabled="currentPage === 0" @click="previousPage">Previous</button>
                <span aria-live="polite">Page {{ currentPage + 1 }} of {{ totalPages }}</span>
                <button type="button" :disabled="currentPage + 1 >= totalPages" @click="nextPage">Next</button>
            </nav>
		</div>
		<!-- <EarnedOvertimeCard :balance="balance" /> -->
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