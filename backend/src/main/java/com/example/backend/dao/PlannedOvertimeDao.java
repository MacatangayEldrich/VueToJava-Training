package com.example.backend.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "planned_overtime")
public class PlannedOvertimeDao {
    @Id
    @Column(name = "po_id")
    private Long poId;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "workday_id", nullable = false)
    private Long workdayId;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "po_description")
    private String poDescription;

    @Column(name = "planned_hours", nullable = false)
    private Integer plannedHours;

    @Column(name = "filing_date", nullable = false)
    private LocalDate filingDate;

    @Column(name = "planned_date", nullable = false)
    private LocalDate plannedDate;

    @Column(name = "po_status", nullable = false)
    private String poStatus;

    protected PlannedOvertimeDao() {}

    public PlannedOvertimeDao(Long poId, Long employeeId, Long workdayId, Long managerId, String poDescription,
                              Integer plannedHours, LocalDate filingDate, LocalDate plannedDate, String poStatus) {
        this.poId = poId;
        this.employeeId = employeeId;
        this.workdayId = workdayId;
        this.managerId = managerId;
        this.poDescription = poDescription;
        this.plannedHours = plannedHours;
        this.filingDate = filingDate;
        this.plannedDate = plannedDate;
        this.poStatus = poStatus;
    }

    public Long getPoId() { return poId; }
    public Long getEmployeeId() { return employeeId; }
    public Long getWorkdayId() { return workdayId; }
    public Long getManagerId() { return managerId; }
    public String getPoDescription() { return poDescription; }
    public Integer getPlannedHours() { return plannedHours; }
    public LocalDate getFilingDate() { return filingDate; }
    public LocalDate getPlannedDate() { return plannedDate; }
    public String getPoStatus() { return poStatus; }
}
