import { defineStore } from 'pinia'
import { ref } from 'vue'

export interface ApprovalListItem {
    requestId: number
    requestType: string
    employeeId: number
    employeeName: string
    fillingDate: string
    status: string
}

export const useApprovalListStore = defineStore(
    'approvalList',
    () => {
        const requests = ref<ApprovalListItem[]>([])
        const loading = ref(false)
        const error = ref<string | null>(null)

        const loadApprovalList = async(
            employeeId: number
        ) => {
            loading.value = true
            error.value = null

            try {
                const response = await fetch (`/v1/approval-list/manager/${employeeId}`)

                if(!response.ok) {
                    throw new Error(`Failed to load (${response.status})`)
                }

                requests.value = await response.json()
            } catch (e) {
                error.value = e instanceof Error ? e.message : 'Unknown error'
            } finally {
                loading.value = false
            }

        }

        return {
            requests,
            loading,
            error,
            loadApprovalList
        }
    }
)