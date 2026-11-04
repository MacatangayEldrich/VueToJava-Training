<script setup lang="ts">
import { useOvertimeRequestStore } from '../../stores/overtimeRequestStore'

const overtimeStore = useOvertimeRequestStore()

const props = defineProps<{ request: any, employeeId: number }>()

const approveRequest = async () => {
    await overtimeStore.approvePlannedRequest(props.request.poId, props.employeeId)

    emit('close')
}

const rejectRequest = async () => {
    await overtimeStore.rejectPlannedRequest(props.request.poId, props.employeeId)

    emit('close')
}

const emit = defineEmits<{ close: []}>()
</script>
<template>
    <form class="content-section overtime-form plan-form">
        <div class="section-heading">
            <div><h2>Planned Overtime Request Details</h2></div>
        </div>
        <label>
            Request Type
            <div class="field-value">{{ props.request?.requestType }}</div>
        </label>
        <label>
            Request Date
            <div class="field-value">{{ props.request?.fillingDate }}</div>
        </label>
        <label>
            Employee Name
            <div class="field-value">{{ props.request?.employeeName }}</div>
        </label>
        <label>
            Department
            <div class="field-value">{{ props.request?.department }}</div>
        </label>
        <div class="form-row">
            <label>
                Planned Date
                <div class="field-value">{{ props.request?.plannedDate }}</div>
            </label>
            <label>
                Planned hours
                <div class="field-value">{{ props.request?.plannedHours }}</div>
            </label>
        </div>
        <label>
            Workday Type
            <div class="field-value">{{ props.request?.workdayName }}</div>
        </label>
        <label>
            Work / reason
            <div class="field-value">{{ props.request?.activity }}</div>
        </label>
        <label>
            Remarks
            <div class="field-value">{{ props.request?.remarks }}</div>
        </label>        
        <div class="form-row">
            <button class="submit-button coral-submit" type="button" @click="rejectRequest">Reject</button>
            <button class="submit-button" type="button" @click="approveRequest">Approve</button>
        </div>
    </form>
</template>