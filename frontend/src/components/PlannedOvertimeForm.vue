<script setup lang="ts">
import { ref } from 'vue'

interface PlannedOvertimeInput {
	poId: number
	plannedDate: string
	plannedHours: number
	poDescription: string
}

const props = defineProps<{ today: string }>()
const emit = defineEmits<{ submit: [plan: PlannedOvertimeInput] }>()

const form = ref<PlannedOvertimeInput>({ poId: 0, plannedDate: props.today, plannedHours: 1, poDescription: '' })

function submitForm() {
	alert('Values: ' + JSON.stringify(form.value))
	emit('submit', { ...form.value })
	form.value = { poId: 0, plannedDate: props.today, plannedHours: 1, poDescription: '' }
}
</script>

<template>
	<form class="content-section overtime-form plan-form" @submit.prevent="submitForm">
		<div class="section-heading">
			<div><p class="eyebrow">Before you work</p><h2>Plan overtime</h2></div>
			<span class="form-index">01</span>
		</div>
		<label>
			Planned date
			<input v-model="form.plannedDate" type="date" required />
		</label>
		<div class="form-row">
			<label>
				Planned hours
				<input v-model.number="form.plannedHours" type="number" min="0.25" step="0.25" required />
			</label>
			<label>
				Work / reason
				<input v-model="form.poDescription" type="text" maxlength="80" placeholder="e.g. Release support" required />
			</label>
			<input v-model="form.poId" type="hidden" />
		</div>
		<button class="submit-button" type="submit">Add planned overtime <span aria-hidden="true">+</span></button>
	</form>
</template>
