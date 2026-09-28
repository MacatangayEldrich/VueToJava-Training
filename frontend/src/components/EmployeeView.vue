<script setup lang="ts">
import { computed, ref } from 'vue'

interface PlannedOvertime {
  id: number
  date: string
  hours: number
  activity: string
}

interface OvertimeEntry {
  id: number
  dateEarned: string
  factorRate: number
  overtimeHours: number
  ctoHours: number
  expirationDate: string
}

function localDateValue(date: Date) {
  const year = date.getFullYear()
  const month = String(date.getMonth() + 1).padStart(2, '0')
  const day = String(date.getDate()).padStart(2, '0')
  return `${year}-${month}-${day}`
}

const today = localDateValue(new Date())
const nextId = ref(1)
const plannedOvertime = ref<PlannedOvertime[]>([])
const overtimeEntries = ref<OvertimeEntry[]>([])
const planForm = ref({ date: today, hours: 1, activity: '' })
const actualForm = ref({ dateEarned: today, factorRate: 1.5, overtimeHours: 1 })

const availableBalance = computed(() => overtimeEntries.value
  .filter((entry) => entry.expirationDate >= today)
  .reduce((total, entry) => total + entry.ctoHours, 0))

function expirationDate(dateValue: string) {
  const [year, month, day] = dateValue.split('-').map(Number)
  const expiry = new Date(year, month - 1, day)
  expiry.setFullYear(expiry.getFullYear() + 1)
  if (expiry.getMonth() !== month - 1) {
    expiry.setDate(0)
  }
  return localDateValue(expiry)
}

function formatDate(dateValue: string) {
  return new Date(`${dateValue}T00:00:00`).toLocaleDateString(undefined, {
    year: 'numeric',
    month: 'short',
    day: 'numeric',
  })
}

function submitPlan() {
  plannedOvertime.value.unshift({
    id: nextId.value++,
    ...planForm.value,
  })
  planForm.value = { date: today, hours: 1, activity: '' }
}

function submitActual() {
  const ctoHours = Math.round(actualForm.value.overtimeHours * actualForm.value.factorRate * 100) / 100
  overtimeEntries.value.unshift({
    id: nextId.value++,
    ...actualForm.value,
    ctoHours,
    expirationDate: expirationDate(actualForm.value.dateEarned),
  })
  actualForm.value = { dateEarned: today, factorRate: 1.5, overtimeHours: 1 }
}
</script>

<template>
  <section class="route-view employee-view" aria-labelledby="employee-title">
    <div class="view-heading overtime-heading">
      <div>
        <p class="eyebrow">Employee space <span class="heading-divider">/</span> Overtime &amp; comp time</p>
        <h1 id="employee-title" class="page-title">Make time<br /><em>count.</em></h1>
        <p class="view-lede">Plan overtime, log what you worked, and keep your CTO balance in view.</p>
      </div>
      <div class="avatar" aria-label="Alex Morgan">AM</div>
    </div>

    <div class="overtime-forms">
      <form class="content-section overtime-form plan-form" @submit.prevent="submitPlan">
        <div class="section-heading">
          <div><p class="eyebrow">Before you work</p><h2>Plan overtime</h2></div>
          <span class="form-index">01</span>
        </div>
        <label>
          Planned date
          <input v-model="planForm.date" type="date" required />
        </label>
        <div class="form-row">
          <label>
            Planned hours
            <input v-model.number="planForm.hours" type="number" min="0.25" step="0.25" required />
          </label>
          <label>
            Work / reason
            <input v-model="planForm.activity" type="text" maxlength="80" placeholder="e.g. Release support" required />
          </label>
        </div>
        <button class="submit-button" type="submit">Add planned overtime <span aria-hidden="true">+</span></button>
      </form>

      <form class="content-section overtime-form actual-form" @submit.prevent="submitActual">
        <div class="section-heading">
          <div><p class="eyebrow">After you work</p><h2>Log actual overtime</h2></div>
          <span class="form-index coral-index">02</span>
        </div>
        <label>
          Date worked
          <input v-model="actualForm.dateEarned" type="date" required />
        </label>
        <div class="form-row">
          <label>
            Overtime hours
            <input v-model.number="actualForm.overtimeHours" type="number" min="0.25" step="0.25" required />
          </label>
          <label>
            Factor rate
            <select v-model.number="actualForm.factorRate">
              <option :value="1">1.0×</option>
              <option :value="1.5">1.5×</option>
              <option :value="2">2.0×</option>
            </select>
          </label>
        </div>
        <button class="submit-button coral-submit" type="submit">Record actual overtime <span aria-hidden="true">+</span></button>
        <p class="form-hint">CTO earned = overtime hours × factor rate. CTO expires 12 months after earning.</p>
      </form>
    </div>

    <section class="content-section plans-section" aria-labelledby="plans-title">
      <div class="section-heading">
        <div><p class="eyebrow">Coming up</p><h2 id="plans-title">Planned overtime</h2></div>
        <span class="count-badge">{{ String(plannedOvertime.length).padStart(2, '0') }}</span>
      </div>
      <div v-if="plannedOvertime.length" class="plan-list">
        <article v-for="plan in plannedOvertime" :key="plan.id" class="plan-item">
          <time>{{ formatDate(plan.date) }}</time>
          <strong>{{ plan.activity }}</strong>
          <span>{{ plan.hours }} planned hours</span>
        </article>
      </div>
      <p v-else class="empty-state">No overtime planned yet. Add a plan above to keep track of upcoming work.</p>
    </section>

    <section class="content-section ledger-section" aria-labelledby="ledger-title">
      <div class="section-heading">
        <div><p class="eyebrow">Your earned comp time</p><h2 id="ledger-title">CTO ledger</h2></div>
        <span class="ledger-note">Expired hours excluded from balance</span>
      </div>
      <div class="table-scroll">
        <table class="overtime-table">
          <thead>
            <tr>
              <th scope="col">Date earned</th>
              <th scope="col">Factor rate</th>
              <th scope="col">Overtime hours rendered</th>
              <th scope="col">CTO hours earned</th>
              <th scope="col">CTO expiration date</th>
            </tr>
          </thead>
          <tbody v-if="overtimeEntries.length">
            <tr v-for="entry in overtimeEntries" :key="entry.id">
              <td>{{ formatDate(entry.dateEarned) }}</td>
              <td>{{ entry.factorRate.toFixed(1) }}×</td>
              <td>{{ entry.overtimeHours.toFixed(2) }}</td>
              <td class="earned-hours">{{ entry.ctoHours.toFixed(2) }}</td>
              <td>{{ formatDate(entry.expirationDate) }}</td>
            </tr>
          </tbody>
          <tbody v-else>
            <tr><td class="table-empty" colspan="5">No actual overtime recorded yet.</td></tr>
          </tbody>
        </table>
      </div>
      <footer class="balance-footer">
        <div><span class="eyebrow">Available CTO balance</span><small>Hours available to take</small></div>
        <strong>{{ availableBalance.toFixed(2) }} <small>hrs</small></strong>
      </footer>
    </section>
  </section>
</template>