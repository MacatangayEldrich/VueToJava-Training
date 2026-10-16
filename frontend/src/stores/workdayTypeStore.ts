import { defineStore } from 'pinia'
import { ref } from 'vue'

export interface WorkdayType {
    workdayId: number
    workdayName: string
    factorRate: number
}

export const useWorkdayTypeStore = defineStore('workdayType', () => {
    const workdayTypes = ref<WorkdayType[]>([])
    const loaded = ref(false)
    const loading = ref(false)
    const error = ref<string | null>(null)
    let loadPromise: Promise<void> | null = null

    const loadWorkdayTypes = (): Promise<void> => {
        if (loaded.value) return Promise.resolve()
        if (loadPromise) return loadPromise

        loading.value = true
        error.value = null
        loadPromise = (async () => {
            try {
                const response = await fetch('/v1/workday-types')
                if (!response.ok) {
                    throw new Error(`Failed to load workday types (${response.status})`)
                }

                workdayTypes.value = await response.json() as WorkdayType[]
                loaded.value = true
            } catch (cause) {
                error.value = cause instanceof Error ? cause.message : 'Failed to load workday types'
                throw cause
            } finally {
                loading.value = false
                loadPromise = null
            }
        })()

        return loadPromise
    }

    return {
        workdayTypes,
        loaded,
        loading,
        error,
        loadWorkdayTypes,
    }
})
export const useWorkdayTypeStore = defineStore(
    'workdayType',
    () => {
        const request = ref(null)
        const loading = ref(false)
        const error = ref<string | null>(null)

        const loadWorkdayType = async(
            requestId: number
        ) => {
            loading.value = true
            error.value = null
            
            try {
                const response = await fetch (`/v1/workday-types/${requestId}`)

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
            loadWorkdayType
        }
    }
)
