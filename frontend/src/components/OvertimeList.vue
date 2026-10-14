<script setup lang="ts">
import EarnedOvertimeCard from './EarnedOvertimeCard.vue'
import type { OvertimeEntry } from '../models/overtime'

defineProps<{
	entries: OvertimeEntry[]
	balance: number
}>()

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
						<th scope="col">Overtime hours rendered</th>
						<th scope="col">CTO hours earned</th>
						<th scope="col">CTO expiration date</th>
					</tr>
				</thead>
				<tbody v-if="entries.length">
					<tr v-for="entry in entries" :key="entry.id">
						<td>{{ formatDate(entry.dateEarned) }}</td>
						<td>{{ entry.factorRate.toFixed(2) }}</td>
						<td>{{ entry.overtimeHours.toFixed(2) }}</td>
						<td class="earned-hours">{{ entry.ctoHours.toFixed(2) }}</td>
						<td>{{ formatDate(entry.expirationDate) }}</td>
					</tr>
				</tbody>
				<tbody v-else>
					<tr><td class="table-empty" colspan="5">No actual overtime recorded yet.</td></tr>
				</tbody>
			</table>
		</div>
		<EarnedOvertimeCard :balance="balance" />
	</section>
</template>
