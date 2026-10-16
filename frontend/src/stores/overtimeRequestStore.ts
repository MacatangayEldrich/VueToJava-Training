import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useOvertimeRequestStore = defineStore(
    'overtimeRequest',
    () => {
        const request = ref(null)
        const loading = ref(false)
        const error = ref<string | null>(null)

        const loadOvertimeDetails = async(
            requestId: number
        ) => {
            loading.value = true
            error.value = null
            
            try {
                const response = await fetch (`/v1/planned-overtimes/${requestId}`)

                if(!response.ok) {
                    throw new Error(`Failed to load (${response.status})`)
                }

                request.value = await response.json()
            } catch (e) {
                error.value = e instanceof Error ? e.message : 'Unknown error'
            } finally {
                loading.value = false
            }

        }

        return {
            request,
            loading,
            error,
            loadOvertimeDetails
        }
    }
)