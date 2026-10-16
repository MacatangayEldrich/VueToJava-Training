<script setup lang="ts">
import { onMounted, ref, computed } from 'vue'
import { useApprovalListStore } from '../stores/approvalListStore'
import { useOvertimeRequestStore } from '../stores/overtimeRequestStore'
import { useEmployeeStore } from '../stores/employeeStore'
import { useWorkdayTypeStore } from '../stores/workdayTypeStore'
import RequestDetails from './RequestDetails.vue'

const overtimeStore = useOvertimeRequestStore()
const approvalStore = useApprovalListStore()
const employeeStore = useEmployeeStore()
const workdayTypeStore = useWorkdayTypeStore()

const showPopup = ref(false)

const viewIcon = '\u{1F50D}'

const openRequest = async (request: any) => {
    await overtimeStore.loadOvertimeDetails(request.requestId)

	await employeeStore.loadEmployee(overtimeStore.request.employeeId)

	await workdayTypeStore.loadWorkdayType(overtimeStore.request.workdayId)

    showPopup.value = true
}

onMounted(async () => {
    await approvalStore.loadApprovalList()  
})

const requestDetails = computed(() => ({
	...overtimeStore.request,
	employeeName: employeeStore.request?.employeeName,
	department: employeeStore.request?.department,
	workdayName: workdayTypeStore.request?.workdayName
}))
</script>

<template>
	<section class="content-section ledger-section" aria-labelledby="ledger-title">
		<div class="section-heading">
			<div><h2 id="ledger-title">Approval Queue</h2></div>
		</div>
		<div class="table-scroll approval-table">
			<RequestDetails :show="showPopup" :request="requestDetails" @close="showPopup = false" />
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
					<tr v-for="request in approvalStore.requests" :key="request.requestId">
						<td>{{ request.requestId }}</td>
						<td>{{ request.requestType }}</td>
						<td>{{ request.employeeName }}</td>
						<td>{{ request.fillingDate }}</td>
						<td>{{ request.status }}</td>
						<td><button @click="openRequest(request)">{{ viewIcon }}</button></td>
					</tr>
				</tbody>
				<tbody>
					<tr><td class="table-empty" colspan="5">Showing 1-{{ approvalStore.requests.length}} of {{ approvalStore.requests.length}} records</td></tr>
				</tbody>
			</table>
		</div>
	</section>
</template>

<style scoped>
.approval-table {
	max-height: 500px;
	overflow-y: auto;
}

.overtime-table thead th {
	position: sticky;
	top: 0;
	background: rgba(203, 212, 202, 0.72);
	z-index: 1;
}
</style>