<script setup lang="ts">
import { ref, computed, watch } from 'vue'
import { useApprovalListStore } from '../stores/approvalListStore'
import type { ApprovalListItem } from '../stores/approvalListStore'
import { useOvertimeRequestStore } from '../stores/overtimeRequestStore'
import { useMasterDataStore } from '../stores/masterDataStore'
import RequestDetails from './RequestDetails.vue'
import { useOvertimeSyncStore } from '../stores/overtimeSyncStore'

const overtimeStore = useOvertimeRequestStore()
const approvalStore = useApprovalListStore()
const masterDataStore = useMasterDataStore()
const overtimeSyncStore = useOvertimeSyncStore()

const showPopup = ref(false)
const detailError = ref<string | null>(null)
const selectedRequestType = ref<string | null>(null)

const viewIcon = '\u{1F50D}'

const openRequest = async (request: ApprovalListItem) => {
	if (request.requestType !== 'Overtime Request') return // for removal upon Actual Overtime approval implementation
	selectedRequestType.value = request.requestType
    detailError.value = null
    try {
		await overtimeStore.loadOvertimeDetails(request.requestId)
        if (overtimeStore.error) throw new Error(overtimeStore.error)
        const details = overtimeStore.request
        if (!details) throw new Error('Unable to load request details.')

        await Promise.all([
			masterDataStore.loadEmployee(details.employeeId),
			masterDataStore.loadWorkdayType(details.workdayId)
        ])
        showPopup.value = true
    } catch (cause) {
        detailError.value = cause instanceof Error ? cause.message : 'Unable to load request details.'
    }
}

watch(
	() => overtimeSyncStore.changeVersion,
	() => approvalStore.loadApprovalList(),
	{ immediate: true }
)

const requestDetails = computed(() => ({
	...(overtimeStore.request ?? {}),
	requestType: selectedRequestType.value,
	employeeName: overtimeStore.request
		? masterDataStore.employees[overtimeStore.request.employeeId]?.employeeName
		: undefined,
	department: overtimeStore.request
		? masterDataStore.employees[overtimeStore.request.employeeId]?.department
		: undefined,
	workdayName: masterDataStore.workdayTypes.find(
		(type) => type.workdayId === overtimeStore.request?.workdayId
	)?.workdayName
}))
</script>

<template>
	<section class="content-section ledger-section" aria-labelledby="ledger-title">
		<div class="section-heading">
			<div><h2 id="ledger-title">Approval Queue</h2></div>
		</div>
		<div class="table-scroll approval-table">
			<RequestDetails :show="showPopup" :request="requestDetails" @close="showPopup = false" />
			<p v-if="detailError" class="form-hint" role="alert">{{ detailError }}</p>
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