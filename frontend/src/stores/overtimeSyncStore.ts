import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useOvertimeSyncStore = defineStore('overtimeSync', () => {
  const changeVersion = ref(0)

  function notifyOvertimeChanged() {
    changeVersion.value += 1
  }

  return { changeVersion, notifyOvertimeChanged }
})