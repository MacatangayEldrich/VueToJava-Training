<script setup lang="ts">
import { useOvertimeRequestStore } from '../stores/overtimeRequestStore'

const overtimeStore = useOvertimeRequestStore()

const props = defineProps<{ request: any }>()

const approveRequest = async () => {
    await overtimeStore.approveRequest(props.request.poId)

    emit('close')
}

const rejectRequest = async () => {
    await overtimeStore.rejectRequest(props.request.poId)

    emit('close')
}

const emit = defineEmits<{ close: []}>()
</script>
<template>
    <form class="content-section overtime-form plan-form">
        <div class="section-heading">
            <div><h2>Request Details</h2></div>
        </div>
        <label>
            Request Type
            <div class="field-value">{{ props.request?.poId }}</div>
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
            <div class="field-value">{{ props.request?.otDescription }}</div>
        </label>
        <div class="form-row">
            <button class="submit-button coral-submit" type="button" @click="rejectRequest">Reject</button>
            <button class="submit-button" type="button" @click="approveRequest">Approve</button>
        </div>
    </form>
</template>