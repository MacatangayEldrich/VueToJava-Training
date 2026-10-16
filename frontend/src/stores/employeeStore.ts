import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useEmployeeStore = defineStore(
    'employee',
    () => {
        const request = ref(null)
        const loading = ref(false)
        const error = ref<string | null>(null)

        const loadEmployee = async(
            requestId: number
        ) => {
            loading.value = true
            error.value = null
            
            try {
                const response = await fetch (`/v1/employees/${requestId}`)

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
            loadEmployee
        }
    }
)