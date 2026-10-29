<script setup lang="ts">
import ActualOTRequestApprovalDetails from './ActualOTRequestApprovalDetails.vue';
import PlannedOTRequestApprovalDetails from './PlannedOTRequestApprovalDetails.vue';

const props = defineProps<{ 
    show: boolean,
    request: any,
    requestType: string,
    employeeId: number
}>()

const emit = defineEmits<{ close: []}>()

</script>

<template>
    <div v-if="show" class="modal-overlay" @click.self="emit('close')">
        <div class="modal">
            <button class="close-button" @click="emit('close')">
                X
            </button>
            <PlannedOTRequestApprovalDetails :request="props.request" :employee-id="props.employeeId" v-if="props.requestType === 'Overtime Request'" @close="emit('close')"/>
            <ActualOTRequestApprovalDetails :request="props.request" :employee-id="props.employeeId" v-if="props.requestType === 'Actual Overtime'" @close="emit('close')"/>
        </div>
    </div>
</template>

<style scoped>
.modal-overlay {
    position: fixed;
    inset: 0;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 9999;
}

.modal {
    background: white;
    padding: 2rem;
    border-radius: 12px;
    min-width: 400px;
    position: relative;
}

.close-button {
    position: absolute;
    top: 12px;
    right: 12px;
}
</style>