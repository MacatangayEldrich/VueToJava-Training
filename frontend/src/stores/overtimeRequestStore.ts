import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { PlannedOvertime } from '../models/overtime'

export const useOvertimeRequestStore = defineStore(
    'overtimeRequest',
    () => {
        const request = ref<PlannedOvertime | null>(null)
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

        const approveRequest = async(
            requestId: number
        ) => {
            try {
                const response = await fetch (`/v1/planned-overtimes/${requestId}/approve`,
                    {
                        method: 'PUT'
                    }
                )

                if(!response.ok) {
                    throw new Error(`Failed to approve (${response.status})`)
                }

                request.value = await response.json()
            } catch (e) {
                error.value = e instanceof Error ? e.message : 'Unknown error'
            }

        }

        const rejectRequest = async(
            requestId: number
        ) => {
            try {
                const response = await fetch (`/v1/planned-overtimes/${requestId}/reject`,
                    {
                        method: 'PUT'
                    }
                )

                if(!response.ok) {
                    throw new Error(`Failed to reject (${response.status})`)
                }

                request.value = await response.json()
            } catch (e) {
                error.value = e instanceof Error ? e.message : 'Unknown error'
            }

        }

        return {
            request,
            loading,
            error,
            loadOvertimeDetails,
            approveRequest,
            rejectRequest
        }
    }
)