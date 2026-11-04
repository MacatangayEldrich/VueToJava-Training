<script setup lang="ts">
defineProps<{ balance: number }>()
</script>

<template>
	<footer class="balance-footer">
		<div><span class="eyebrow">Available CTO balance</span><small>Hours available to take</small></div>
		<strong>{{ balance.toFixed(2) }} <small>hrs</small></strong>
	</footer>
</template>