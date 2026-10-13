import { defineStore } from 'pinia'
import { ref } from 'vue'
import { useWorkdayTypeStore } from './workdayTypeStore'

export const useMasterDataStore = defineStore('masterData', () => {
    const loaded = ref(false)
    const loading = ref(false)

    const loadAll = async (): Promise<void> => {
        if (loaded.value) return

        loading.value = true
        const workdayTypeStore = useWorkdayTypeStore()
        try {
            await Promise.all([workdayTypeStore.loadWorkdayTypes()])
            loaded.value = true
        } finally {
            loading.value = false
        }
    }

    return { loaded, loading, loadAll }

})
