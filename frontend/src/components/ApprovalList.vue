<script setup lang="ts">
import { onMounted } from 'vue'
import { useApprovalListStore } from '../stores/approvalListStore'

const store = useApprovalListStore()

onMounted(async () => {
    await store.loadApprovalList()  
})
</script>

<template>
	<section class="content-section ledger-section" aria-labelledby="ledger-title">
		<div class="section-heading">
			<div><h2 id="ledger-title">Approval Queue</h2></div>
		</div>
		<div class="table-scroll">
			<table class="overtime-table">
				<thead>
					<tr>
						<th scope="col">Request ID</th>
						<th scope="col">Type</th>
						<th scope="col">Employee</th>
						<th scope="col">Request Date</th>
						<th scope="col">Status</th>
                        <th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="request in store.requests" :key=request.requestId>
						<td>{{ request.requestId }}</td>
						<td>{{ request.requestType }}</td>
						<td>{{ request.employeeName }}</td>
						<td>{{ request.fillingDate }}</td>
						<td>{{ request.status }}</td>
						<td>View</td>
					</tr>
				</tbody>
				<tbody>
					<tr><td class="table-empty" colspan="5">Showing 1-{{ store.requests.length}} of {{ store.requests.length}} records</td></tr>
				</tbody>
			</table>
		</div>
	</section>
</template>