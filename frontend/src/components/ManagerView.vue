<script setup lang="ts">
import ApprovalList from '../components/ApprovalList.vue'
import RequestDetails from '../components/RequestDetails.vue'

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

    <ApprovalList />
    <RequestDetails />
  </section>
</template>