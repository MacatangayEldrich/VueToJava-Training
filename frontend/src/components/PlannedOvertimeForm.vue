<script setup lang="ts">
import { ref, watch } from 'vue'
import { storeToRefs } from 'pinia'
import { useWorkdayTypeStore } from '../stores/workdayTypeStore'
import type { PlannedOvertime, PlannedOvertimeCreateRequest, PlannedOvertimeFormValues } from '../models/overtime'

const workdayTypeStore = useWorkdayTypeStore()
const { workdayTypes } = storeToRefs(workdayTypeStore)

const props = defineProps<{ today: string; employeeId: number }>()
const emit = defineEmits<{ submit: [plan: PlannedOvertime] }>()
const selectedWorkdayId = ref<number | null>(null)
const submitting = ref(false)
const errorMessage = ref('')

const form = ref<PlannedOvertimeFormValues>({ plannedDate: props.today, plannedHours: 1, activity: '' })

watch(workdayTypes, (types) => {
	if (selectedWorkdayId.value === null && types.length > 0) {
		selectedWorkdayId.value = types[0].workdayId
	}
}, { immediate: true })

async function submitForm() {
	const selectedType = workdayTypes.value.find((type) => type.workdayId === selectedWorkdayId.value)
	if (!selectedType || submitting.value) return

	const request: PlannedOvertimeCreateRequest = {
		poId: Date.now(),
		employeeId: props.employeeId,
		workdayId: selectedType.workdayId,
		activity: form.value.activity,
		plannedHours: form.value.plannedHours,
		fillingDate: props.today,
		plannedDate: form.value.plannedDate,
		status: 'For Approval',
	}

	submitting.value = true
	errorMessage.value = ''
	try {
		const response = await fetch('/v1/planned-overtimes', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(request),
		})
		if (!response.ok) {
			throw new Error(`Unable to submit planned overtime (${response.status})`)
		}

		const createdPlan = await response.json() as PlannedOvertime
		emit('submit', createdPlan)
		form.value = { plannedDate: props.today, plannedHours: 1, activity: '' }
	} catch (error) {
		errorMessage.value = error instanceof Error ? error.message : 'Unable to submit planned overtime.'
	} finally {
		submitting.value = false
	}
}
</script>

<template>
	<form class="content-section overtime-form plan-form" @submit.prevent="submitForm">
		<div class="section-heading">
			<div><p class="eyebrow">Before you work</p><h2>Plan overtime</h2></div>
			<span class="form-index">01</span>
		</div>
		<div class="form-row">
			<label>
				Planned date
				<input v-model="form.plannedDate" type="date" required />
			</label>
			<label>
				Planned hours
				<input v-model.number="form.plannedHours" type="number" min="1" step="1" required />
			</label>
			<label>
				Workday type
				<select v-model.number="selectedWorkdayId" required :disabled="workdayTypeStore.loading || workdayTypes.length === 0">
					<option :value="null" disabled>Select a workday type</option>
					<option v-for="type in workdayTypes" :key="type.workdayId" :value="type.workdayId">
						{{ type.workdayName }} ({{ type.factorRate }}×)
					</option>
				</select>
			</label>
			<label>
				Work / reason
				<input v-model="form.activity" type="text" placeholder="e.g. Release support" required />
			</label>
		</div>
		<p v-if="workdayTypeStore.loading" class="form-hint">Loading workday types…</p>
		<p v-else-if="workdayTypeStore.error" class="form-hint" role="alert">{{ workdayTypeStore.error }}</p>
		<p v-else-if="workdayTypes.length === 0" class="form-hint">No workday types are available.</p>
		<p v-if="errorMessage" class="form-hint" role="alert">{{ errorMessage }}</p>
		<button class="submit-button" type="submit" :disabled="submitting || workdayTypeStore.loading || workdayTypes.length === 0">
			{{ submitting ? 'Submitting…' : 'Add planned overtime' }} <span v-if="!submitting" aria-hidden="true">+</span>
		</button>
	</form>
</template>
