import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { PlannedOvertime, ActualOvertime } from '../models/overtime'
import { useOvertimeSyncStore } from './overtimeSyncStore'

export const useOvertimeRequestStore = defineStore(
    'overtimeRequest',
    () => {
        const plannedRequest = ref<PlannedOvertime | null>(null)
        const actualRequest = ref<ActualOvertime | null>(null)
        const loading = ref(false)
        const error = ref<string | null>(null)
        const overtimeSyncStore = useOvertimeSyncStore()

        const loadPlannedOvertimeDetails = async(
            requestId: number
        ) => {
            loading.value = true
            error.value = null
            
            try {
                const response = await fetch (`/v1/planned-overtimes/${requestId}`)

                if(!response.ok) {
                    throw new Error(`Failed to load (${response.status})`)
                }

                plannedRequest.value = await response.json()
            } catch (e) {
                error.value = e instanceof Error ? e.message : 'Unknown error'
            } finally {
                loading.value = false
            }

        }

        const loadActualOvertimeDetails = async(
            requestId: number
        ) => {
            loading.value = true
            error.value = null
            
            try {
                const response = await fetch (`/v1/actual-overtimes/${requestId}`)

                if(!response.ok) {
                    throw new Error(`Failed to load (${response.status})`)
                }

                actualRequest.value = await response.json()
            } catch (e) {
                error.value = e instanceof Error ? e.message : 'Unknown error'
            } finally {
                loading.value = false
            }

        }

        const approvePlannedRequest = async(
            requestId: number,
            employeeId: number
        ) => {
            try {
                const response = await fetch (`/v1/planned-overtimes/${requestId}/approve/${employeeId}`,
                    {
                    method: 'PUT'
                    }
                )

                if(!response.ok) {
                    throw new Error(`Failed to approve (${response.status})`)
                }

                plannedRequest.value = await response.json()
                overtimeSyncStore.notifyOvertimeChanged()
            } catch (e) {
                error.value = e instanceof Error ? e.message : 'Unknown error'
            }

        }

        const rejectPlannedRequest = async(
            requestId: number,
            employeeId: number
        ) => {
            try {
                const response = await fetch (`/v1/planned-overtimes/${requestId}/reject/${employeeId}`,
                    {
                    method: 'PUT'
                    }
                )

                if(!response.ok) {
                    throw new Error(`Failed to reject (${response.status})`)
                }

                plannedRequest.value = await response.json()
                overtimeSyncStore.notifyOvertimeChanged()
            } catch (e) {
                error.value = e instanceof Error ? e.message : 'Unknown error'
            }

        }

        const approveActualRequest = async(
            requestId: number,
            employeeId: number
        ) => {
            try {
                const response = await fetch (`/v1/actual-overtimes/${requestId}/approve/${employeeId}`,
                    {
                        method: 'PUT'
                    }
                )

                if(!response.ok) {
                    throw new Error(`Failed to approve (${response.status})`)
                }

                actualRequest.value = await response.json()
            } catch (e) {
                error.value = e instanceof Error ? e.message : 'Unknown error'
            }

        }

        const rejectActualRequest = async(
            requestId: number,
            employeeId: number
        ) => {
            try {
                const response = await fetch (`/v1/actual-overtimes/${requestId}/reject/${employeeId}`,
                    {
                        method: 'PUT'
                    }
                )

                if(!response.ok) {
                    throw new Error(`Failed to reject (${response.status})`)
                }

                actualRequest.value = await response.json()
            } catch (e) {
                error.value = e instanceof Error ? e.message : 'Unknown error'
            }

        }

        return {
            plannedRequest,
            actualRequest,
            loading,
            error,
            loadPlannedOvertimeDetails,
            loadActualOvertimeDetails,
            approvePlannedRequest,
            rejectPlannedRequest,
            approveActualRequest,
            rejectActualRequest
        }
    }
)