import { defineStore } from 'pinia'
import { ref } from 'vue'

export interface WorkdayType {
    workdayId: number
    workdayName: string
    factorRate: number
}

export interface Employee {
    employeeId: number
    employeeName: string
    department: string
    position: string
    workDays: string
    workHours: string
}

export const useMasterDataStore = defineStore('masterData', () => {
    const loaded = ref(false)
    const loading = ref(false)
    const workdayTypes = ref<WorkdayType[]>([])
    const workdayTypesLoaded = ref(false)
    const workdayTypesLoading = ref(false)
    const workdayTypesError = ref<string | null>(null)
    const employees = ref<Record<number, Employee>>({})
    const employeeLoading = ref(false)
    const employeeError = ref<string | null>(null)
    let loadWorkdayTypesPromise: Promise<void> | null = null

    const loadAll = async (): Promise<void> => {
        if (loaded.value) return

        loading.value = true
        try {
            await loadWorkdayTypes()
            loaded.value = true
        } finally {
            loading.value = false
        }
    }

    const loadWorkdayTypes = (): Promise<void> => {
        if (workdayTypesLoaded.value) return Promise.resolve()
        if (loadWorkdayTypesPromise) return loadWorkdayTypesPromise

        workdayTypesLoading.value = true
        workdayTypesError.value = null
        loadWorkdayTypesPromise = (async () => {
            try {
                const response = await fetch('/v1/workday-types')
                if (!response.ok) {
                    throw new Error(`Failed to load workday types (${response.status})`)
                }

                workdayTypes.value = await response.json() as WorkdayType[]
                workdayTypesLoaded.value = true
            } catch (cause) {
                workdayTypesError.value = cause instanceof Error
                    ? cause.message
                    : 'Failed to load workday types'
                throw cause
            } finally {
                workdayTypesLoading.value = false
                loadWorkdayTypesPromise = null
            }
        })()

        return loadWorkdayTypesPromise
    }

    const loadWorkdayType = async (workdayId: number): Promise<WorkdayType> => {
        await loadWorkdayTypes()
        const workdayType = workdayTypes.value.find((type) => type.workdayId === workdayId)
        if (!workdayType) {
            throw new Error(`Workday type ${workdayId} was not found`)
        }
        return workdayType
    }

    const loadEmployee = async (employeeId: number): Promise<Employee> => {
        const cachedEmployee = employees.value[employeeId]
        if (cachedEmployee) return cachedEmployee

        employeeLoading.value = true
        employeeError.value = null
        try {
            const response = await fetch(`/v1/employees/${employeeId}`)
            if (!response.ok) {
                throw new Error(`Failed to load employee (${response.status})`)
            }

            const employee = await response.json() as Employee
            employees.value[employeeId] = employee
            return employee
        } catch (cause) {
            employeeError.value = cause instanceof Error
                ? cause.message
                : 'Failed to load employee'
            throw cause
        } finally {
            employeeLoading.value = false
        }
    }

    return {
        loaded,
        loading,
        workdayTypes,
        workdayTypesLoaded,
        workdayTypesLoading,
        workdayTypesError,
        employees,
        employeeLoading,
        employeeError,
        loadAll,
        loadWorkdayTypes,
        loadWorkdayType,
        loadEmployee,
    }

})