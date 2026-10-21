<script setup lang="ts">
import PlannedOTApprovalList from '../components/PlannedOTApprovalList.vue';
import ActualOTApprovalList from '../components/ActualOTApprovalList.vue';

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

    <PlannedOTApprovalList />
    <ActualOTApprovalList />
  </section>
</template>