<script setup lang="ts">
import OvertimeRequestDetails from './OvertimeRequestDetails.vue'

defineProps<{
    request: any
}>()

</script>
<template>
    <form class="content-section overtime-form plan-form" @submit.prevent="submitPlan">
        <div class="section-heading">
            <div><h2>Request Details</h2></div>
        </div>
        <label>
            Request Type
            <div class="field-value">{{request?.requestId}}</div>
        </label>
        <label>
            Request Date
            <div class="field-value">2025-01-05</div>
        </label>
        <label>
            Employee Name
            <div class="field-value">Ava Martinez</div>
        </label>
        <label>
            Department
            <div class="field-value">QA/DEV/APP SUPPORT</div>
        </label>
        <div class="form-row">
            <label>
                Planned Date
                <div class="field-value">2025-01-25</div>
            </label>
            <label>
                Planned hours
                <div class="field-value">8</div>
            </label>
        </div>
        <label>
            Workday Type
            <div class="field-value">Ordinary Day</div>
        </label>
        <label>
            Work / reason
            <div class="field-value">Development</div>
        </label>
        <div class="form-row">
            <button class="submit-button coral-submit" type="submit">Reject</button>
            <button class="submit-button" type="submit">Approve</button>
        </div>
    </form>
</template>