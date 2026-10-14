<script setup lang="ts">
import { ref } from 'vue'
import type { ActualOvertimeInput } from '../models/overtime'

const props = defineProps<{ today: string }>()
const emit = defineEmits<{ submit: [entry: ActualOvertimeInput] }>()

const form = ref<ActualOvertimeInput>({
	dateEarned: props.today,
	factorRate: 1.5,
	overtimeHours: 1,
})

function submitForm() {

	emit('submit', { ...form.value })
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
		</div>
		<button class="submit-button coral-submit" type="submit">Record actual overtime <span aria-hidden="true">+</span></button>
		<p class="form-hint">CTO earned = overtime hours × factor rate. CTO expires 45 days from the day work was rendered.</p>
	</form>
</template>
