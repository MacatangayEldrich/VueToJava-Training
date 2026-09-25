<script setup lang="ts">
import { onMounted, ref } from 'vue'

interface BackendResponse {
  message: string
  detail: string
  timestamp: string
}

const response = ref<BackendResponse | null>(null)
const error = ref<string | null>(null)
const loading = ref(false)
const requestDuration = ref<number | null>(null)

async function checkBackend() {
  loading.value = true
  error.value = null
  const startedAt = performance.now()

  try {
    const result = await fetch('/api/hello')
    if (!result.ok) {
      throw new Error(`Backend returned HTTP ${result.status}`)
    }

    response.value = (await result.json()) as BackendResponse
    requestDuration.value = Math.round(performance.now() - startedAt)
  } catch (reason) {
    response.value = null
    requestDuration.value = null
    error.value = reason instanceof Error ? reason.message : 'Unable to reach the backend.'
  } finally {
    loading.value = false
  }
}

onMounted(checkBackend)
</script>

<template>
  <main class="shell">
    <header class="topbar">
      <a class="brand" href="/" aria-label="Vue to Java home">
        <span class="brand-mark">VJ</span>
        <span>Vue to Java</span>
      </a>
      <span class="environment"><span class="status-dot"></span>Local workspace</span>
    </header>

    <section class="intro">
      <p class="eyebrow">Integration console / 01</p>
      <h1>Frontend, meet<br /><em>backend.</em></h1>
      <p class="lede">A small Vue surface for proving the full request path is alive.</p>
    </section>

    <section class="connection-panel" aria-live="polite">
      <div class="panel-heading">
        <div>
          <p class="eyebrow">Connection check</p>
          <h2>Spring Boot handshake</h2>
        </div>
        <span class="pill" :class="{ connected: response, failed: error }">
          <span class="pill-dot"></span>
          {{ loading ? 'Checking' : response ? 'Connected' : error ? 'Offline' : 'Waiting' }}
        </span>
      </div>

      <div v-if="response" class="result">
        <div class="result-icon">✓</div>
        <div class="result-copy">
          <strong>{{ response.message }}</strong>
          <p>{{ response.detail }}</p>
          <small>Received {{ new Date(response.timestamp).toLocaleTimeString() }}</small>
        </div>
      </div>

      <div v-else-if="error" class="result error-result">
        <div class="result-icon">!</div>
        <div class="result-copy">
          <strong>Connection failed</strong>
          <p>{{ error }}</p>
          <small>Start Spring Boot on port 8080, then try again.</small>
        </div>
      </div>

      <div v-else class="result loading-result">
        <div class="result-icon pulse">...</div>
        <div class="result-copy">
          <strong>Contacting the backend</strong>
          <p>Waiting for a response from <code>/api/hello</code>.</p>
        </div>
      </div>

      <footer class="panel-footer">
        <span><code>GET</code> <code>/api/hello</code></span>
        <span v-if="requestDuration !== null">{{ requestDuration }} ms</span>
        <button type="button" :disabled="loading" @click="checkBackend">
          {{ loading ? 'Checking...' : 'Check again' }} <span aria-hidden="true">↗</span>
        </button>
      </footer>
    </section>

    <footer class="page-footer">
      <span>Vue 3 + TypeScript</span>
      <span class="footer-line"></span>
      <span>Spring Boot 4</span>
    </footer>
  </main>
</template>
