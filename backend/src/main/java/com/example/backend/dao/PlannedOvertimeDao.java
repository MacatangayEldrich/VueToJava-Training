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

    @Column(name = "ot_description")
    private String otDescription;

    @Column(name = "planned_hours", nullable = false)
    private Integer plannedHours;

    @Column(name = "filling_date", nullable = false)
    private LocalDate fillingDate;

    @Column(name = "planned_date", nullable = false)
    private LocalDate plannedDate;

    @Column(name = "po_status", nullable = false)
    private String poStatus;

    protected PlannedOvertimeDao() {}

    public PlannedOvertimeDao(Long poId, Long employeeId, Long workdayId, Long managerId, String otDescription,
                              Integer plannedHours, LocalDate fillingDate, LocalDate plannedDate, String poStatus) {
        this.poId = poId;
        this.employeeId = employeeId;
        this.workdayId = workdayId;
        this.managerId = managerId;
        this.otDescription = otDescription;
        this.plannedHours = plannedHours;
        this.fillingDate = fillingDate;
        this.plannedDate = plannedDate;
        this.poStatus = poStatus;
    }

    public Long getPoId() { return poId; }
    public Long getEmployeeId() { return employeeId; }
    public Long getWorkdayId() { return workdayId; }
    public Long getManagerId() { return managerId; }
    public String getOtDescription() { return otDescription; }
    public Integer getPlannedHours() { return plannedHours; }
    public LocalDate getFillingDate() { return fillingDate; }
    public LocalDate getPlannedDate() { return plannedDate; }
    public String getPoStatus() { return poStatus; }
}
