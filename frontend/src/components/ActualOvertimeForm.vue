<script setup lang="ts">
import { ref } from 'vue'
import type { ActualOvertime, PlannedOvertime } from '../models/overtime'
import { storeToRefs } from 'pinia'
import { useMasterDataStore } from '../stores/masterDataStore'
import axios from 'axios'

const masterDataStore = useMasterDataStore()
const { workdayTypes } = storeToRefs(masterDataStore)
const props = defineProps<{ plan: PlannedOvertime }>()
const emit = defineEmits<{ submit: [entry: ActualOvertime] }>()
const isSubmitted = ref(false)
const today = localDateValue(new Date())
const factorRateValue = workdayTypes.value.find(
	p => p.workdayId === props.plan.workdayId ? p.factorRate : 1 
)?.factorRate


const form = ref<ActualOvertime>({ 
	poId: props.plan.poId,
	status : props.plan.status,
	activity : props.plan.activity,
	renderedHours : props.plan.plannedHours,
	earnedHours : 0,
	fillingDate : today,
	actualDate: props.plan.plannedDate,
	expirationDate : '',
	remarks : ''	
})

 function localDateValue(date: Date) {
	const year = date.getFullYear()
	const month = String(date.getMonth() + 1).padStart(2, '0')
	const day = String(date.getDate()).padStart(2, '0')
	return `${year}-${month}-${day}`
}

function expirationDate(dateValue: string) {
	const [year, month, day] = dateValue.split('-').map(Number)
	const expiry = new Date(year, month - 1, day)
	expiry.setFullYear(expiry.getFullYear() + 1)
	if (expiry.getMonth() !== month - 1) {
		expiry.setDate(0)
	}
	return localDateValue(expiry)
}

async function submitForm() {
	const ctoHours = Math.round(form.value.renderedHours * (factorRateValue) * 100) / 100

	form.value = {
		poId: props.plan.poId,
		status : 'FOR APPROVAL',
		activity : form.value.activity,
		renderedHours : form.value.renderedHours,
		earnedHours : ctoHours,
		fillingDate : today,
		actualDate: form.value.actualDate, 
		expirationDate : expirationDate(form.value.actualDate),
		remarks : ''

	}
	try {
		const buttonText = ref('Submit')
		await axios.post('/v1/actual-overtimes', form.value)
		buttonText.value = 'Submitted'
		isSubmitted.value = true
	} catch (error) {
		console.log('Error creating actual overtime entry.')
	}
}
</script>

<template>
	<form class="content-section overtime-form actual-form" @submit.prevent="submitForm">
		<div class="section-heading">
			<div><h2>Log actual overtime</h2></div>
		</div>
		<div class="form-row">
		<label>
			Date worked
			<input v-model="form.actualDate" type="date" :readOnly="isSubmitted" required />
		</label>
		<label>
			Overtime hours
			<input v-model.number="form.renderedHours" type="number" min="0.25" step="0.25" :readOnly="isSubmitted" required />
		</label>
		</div>
		<label>
			Work / reason
			<input v-model="form.activity" type="text" placeholder="e.g. Release support" :readOnly="isSubmitted" required />
		</label>
		<button class="submit-button coral-submit" :disabled="isSubmitted" type="submit" v-if="!isSubmitted">Record actual overtime<span aria-hidden="true">+</span></button>
		<button class="button actual-submit" :disabled="isSubmitted" type="submit" v-if="isSubmitted" :readOnly="isSubmitted">Submitted<span aria-hidden="true">+</span></button>
		<p class="form-hint">CTO earned = overtime hours × factor rate. CTO expires 45 days from the day work was rendered.</p>
	</form>
</template>