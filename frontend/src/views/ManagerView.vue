<script setup lang="ts">
import ActualOTApprovalList from '../components/ActualOTApprovalList.vue';
import PlannedOTApprovalList from '../components/PlannedOTApprovalList.vue';
import { useMasterDataStore } from '../stores/masterDataStore'

const props = defineProps<{ employeeId: number }>()


const formattedDate = new Intl.DateTimeFormat('en-US', {
  weekday: 'long',
  year: 'numeric',
  month: 'long',
  day: 'numeric'
}).format(new Date)

</script>

<template>
  <section class="route-view" aria-labelledby="manager-title">
    <p class="eyebrow">{{ formattedDate }}<span class="heading-divider">/</span> Manager space</p>

    <PlannedOTApprovalList :employee-id="props.employeeId"/>
    <ActualOTApprovalList :employee-id="props.employeeId"/>
  </section>
</template>