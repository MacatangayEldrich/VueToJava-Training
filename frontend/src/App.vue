<script setup lang="ts">
import { onMounted, onUnmounted, ref } from 'vue'
import EmployeeView from './components/EmployeeView.vue'
import ManagerView from './components/ManagerView.vue'

const backendHealthy = ref(true)
const error = ref<string | null>(null)
const loading = ref(false)
const requestDuration = ref<number | null>(null)
const activeRoute = ref<'employee' | 'manager'>('employee')

function syncRoute() {
  activeRoute.value = window.location.hash === '#/manager' ? 'manager' : 'employee'
}

async function checkBackend() {
  loading.value = true
  error.value = null
  const startedAt = performance.now()
  try {
    const result = await fetch('/actuator/health')
    const health = await result.json()
    backendHealthy.value = result.ok && health.status === 'UP'
    requestDuration.value = Math.round(performance.now() - startedAt)
  } catch {
    backendHealthy.value = false
    error.value= 'Unable to reach the backend.'
    requestDuration.value = null
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  syncRoute()
  window.addEventListener('hashchange', syncRoute)
  checkBackend()
})

onUnmounted(() => window.removeEventListener('hashchange', syncRoute))
</script>

<template>
  <main class="shell">
    <header class="topbar">
      <a class="brand" href="#/employee" aria-label="PeopleDesk home">
        <span class="brand-mark">VJ</span>
        <span>PeopleDesk</span>
      </a>
      <div class="header-tools">
        <span class="environment"><span class="status-dot" :class="{ offline: error }"></span>{{ backendHealthy ? 'System online' : error ? 'System offline' : 'Connecting' }}</span>
        <button class="refresh-button" type="button" :disabled="loading" aria-label="Refresh system status" title="Refresh system status" @click="checkBackend">↻</button>
      </div>
    </header>

    <div class="workspace">
      <aside class="sidebar">
        <p class="eyebrow">Workspace</p>
        <nav class="route-nav" aria-label="Workspace routes">
          <a href="#/employee" :class="{ active: activeRoute === 'employee' }" :aria-current="activeRoute === 'employee' ? 'page' : undefined">
            <span class="nav-icon">E</span>
            <span>Employee</span>
            <span class="nav-arrow" aria-hidden="true">↗</span>
          </a>
          <a href="#/manager" :class="{ active: activeRoute === 'manager' }" :aria-current="activeRoute === 'manager' ? 'page' : undefined">
            <span class="nav-icon">M</span>
            <span>Manager</span>
            <span class="nav-arrow" aria-hidden="true">↗</span>
          </a>
        </nav>
        <div class="sidebar-note">
          <span class="note-mark">+</span>
          <p><strong>People first.</strong><br />A better day starts with a clear view.</p>
        </div>
      </aside>

      <EmployeeView v-show="activeRoute === 'employee'" />
      <ManagerView v-show="activeRoute === 'manager'" />
    </div>

    <footer class="page-footer">
      <span>PeopleDesk <span v-if="requestDuration !== null">· {{ requestDuration }} ms</span></span>
      <span class="footer-line"></span>
      <span>Vue 3 <span class="footer-divider">/</span> Spring Boot</span>
    </footer>
  </main>
</template>
