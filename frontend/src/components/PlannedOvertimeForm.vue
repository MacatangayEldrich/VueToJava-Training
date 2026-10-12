<script setup lang="ts">
import { ref } from 'vue'

interface PlannedOvertimeInput {
	date: string
	hours: number
	activity: string
}

const props = defineProps<{ today: string }>()
const emit = defineEmits<{ submit: [plan: PlannedOvertimeInput] }>()

const form = ref<PlannedOvertimeInput>({ date: props.today, hours: 1, activity: '' })

function submitForm() {
	emit('submit', { ...form.value })
	form.value = { date: props.today, hours: 1, activity: '' }
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
			<input v-model="form.date" type="date" required />
		</label>
		<div class="form-row">
			<label>
				Planned hours
				<input v-model.number="form.hours" type="number" min="0.25" step="0.25" required />
			</label>
			<label>
				Work / reason
				<input v-model="form.activity" type="text" maxlength="80" placeholder="e.g. Release support" required />
			</label>
		</div>
		<button class="submit-button" type="submit">Add planned overtime <span aria-hidden="true">+</span></button>
	</form>
</template>
