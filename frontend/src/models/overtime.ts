export interface OvertimeForm {
    poId: number
    managerId: number
    status: string
    activity: string
    fillingDate: string
}

export interface PlannedOvertime {
    poId: number
    employeeId: number
    workdayId: number
    managerId: number | null
    activity: string
    plannedHours: number
    fillingDate: string
    plannedDate: string
    status: string
}

export interface PlannedOvertimeCreateRequest {
    employeeId: number
    workdayId: number
    activity: string
    plannedHours: number
    fillingDate: string
    plannedDate: string
    status: string
}

export interface PlannedOvertimeFormValues {
    plannedDate: string
    plannedHours: number
    activity: string
}

export interface ActualOvertimeInput {
    dateEarned: string
    overtimeHours: number
    activity: string
}

export interface OvertimeEntry {
    id: number
    dateEarned: string
    factorRate: number
    overtimeHours: number
    ctoHours: number
    expirationDate: string
}

export interface ActualOvertime {
	poId: number
	status : string
	activity : string
	renderedHours : number
	earnedHours : number
	fillingDate : string
	actualDate: string
	expirationDate : string
	remarks : string
}