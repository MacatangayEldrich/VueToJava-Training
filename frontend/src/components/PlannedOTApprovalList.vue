<script setup lang="ts">
import { onMounted, ref, computed } from 'vue'
import { useApprovalListStore } from '../stores/approvalListStore'
import type { ApprovalListItem } from '../stores/approvalListStore'
import { useOvertimeRequestStore } from '../stores/overtimeRequestStore'
import { useMasterDataStore } from '../stores/masterDataStore'
import RequestDetails from './RequestDetails.vue'

const props = defineProps<{ employeeId: number}>()
const overtimeStore = useOvertimeRequestStore()
const approvalStore = useApprovalListStore()
const masterDataStore = useMasterDataStore()

const showPopup = ref(false)
const detailError = ref<string | null>(null)

const viewIcon = '\u{1F50D}'
const requestType = 'Overtime Request'

const openRequest = async (request: ApprovalListItem) => {
    detailError.value = null
    try {
        await overtimeStore.loadPlannedOvertimeDetails(request.requestId)
        if (overtimeStore.error) throw new Error(overtimeStore.error)
        const details = overtimeStore.plannedRequest
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

onMounted(async () => {
    await approvalStore.loadApprovalList(props.employeeId)  
})

const requestDetails = computed(() => ({
	...(overtimeStore.plannedRequest ?? {}),
	employeeName: overtimeStore.plannedRequest
		? masterDataStore.employees[overtimeStore.plannedRequest.employeeId]?.employeeName
		: undefined,
	department: overtimeStore.plannedRequest
		? masterDataStore.employees[overtimeStore.plannedRequest.employeeId]?.department
		: undefined,
	workdayName: masterDataStore.workdayTypes.find(
		(type) => type.workdayId === overtimeStore.plannedRequest?.workdayId
	)?.workdayName
}))
</script>

<template>
	<section class="content-section ledger-section" aria-labelledby="ledger-title">
		<div class="section-heading">
			<div><h2 id="ledger-title">Planned Overtime Approval Queue</h2></div>
		</div>
		<div class="table-scroll approval-table">
			<RequestDetails :show="showPopup" :request="requestDetails" :employee-id="props.employeeId" :requestType="requestType" @close="showPopup = false; approvalStore.loadApprovalList(props.employeeId);" />
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
						<template v-if="request.requestType === 'Overtime Request'">
							<td>{{ request.requestId }}</td>
							<td>{{ request.requestType }}</td>
							<td>{{ request.employeeName }}</td>
							<td>{{ request.fillingDate }}</td>
							<td>{{ request.status }}</td>
							<td><button @click="openRequest(request)">{{ viewIcon }}</button></td>
						</template>
					</tr>
				</tbody>
				<tbody>
					<tr><td class="table-empty" colspan="5">Showing 1-{{ approvalStore.requests.filter(item => item.requestType  === 'Actual Overtime').length}} of {{ approvalStore.requests.filter(item => item.requestType  === 'Actual Overtime').length}} records</td></tr>
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