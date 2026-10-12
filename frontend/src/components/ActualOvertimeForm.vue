<script setup lang="ts">
import { ref, watch } from 'vue'
import { storeToRefs } from 'pinia'
import { useWorkdayTypeStore } from '../stores/workdayTypeStore'

const workdayTypeStore = useWorkdayTypeStore()
const { workdayTypes } = storeToRefs(workdayTypeStore)

interface ActualOvertimeInput {
	dateEarned: string
	factorRate: number
	overtimeHours: number
}

const props = defineProps<{ today: string }>()
const emit = defineEmits<{ submit: [entry: ActualOvertimeInput] }>()
const selectedWorkdayId = ref<number | null>(null)

const form = ref<ActualOvertimeInput>({
	dateEarned: props.today,
	factorRate: 1.5,
	overtimeHours: 1,
})

watch(workdayTypes, (types) => {
	if (selectedWorkdayId.value === null && types.length > 0) {
		selectedWorkdayId.value = types[0].workdayId
	}
}, { immediate: true })

function submitForm() {
	const selectedType = workdayTypes.value.find((type) => type.workdayId === selectedWorkdayId.value)
	if (!selectedType) return

	emit('submit', { ...form.value, factorRate: selectedType.factorRate })
	form.value = { dateEarned: props.today, factorRate: 1.5, overtimeHours: 1 }
}
</script>

<template>
	<form class="content-section overtime-form actual-form" @submit.prevent="submitForm">
		<div class="section-heading">
			<div><p class="eyebrow">After you work</p><h2>Log actual overtime</h2></div>
			<span class="form-index coral-index">02</span>
		</div>
		<label>
			Date worked
			<input v-model="form.dateEarned" type="date" required />
		</label>
		<div class="form-row">
			<label>
				Overtime hours
				<input v-model.number="form.overtimeHours" type="number" min="0.25" step="0.25" required />
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
		</div>
		<p v-if="workdayTypeStore.loading" class="form-hint">Loading workday types…</p>
		<p v-else-if="workdayTypeStore.error" class="form-hint" role="alert">{{ workdayTypeStore.error }}</p>
		<p v-else-if="workdayTypes.length === 0" class="form-hint">No workday types are available.</p>
		<button class="submit-button coral-submit" type="submit">Record actual overtime <span aria-hidden="true">+</span></button>
		<p class="form-hint">CTO earned = overtime hours × factor rate. CTO expires 12 months after earning.</p>
	</form>
</template>
