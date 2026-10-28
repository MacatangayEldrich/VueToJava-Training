package com.example.backend.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "planned_overtime")
public class PlannedOvertimeDao {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "po_id")
    private Long poId;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "workday_id", nullable = false)
    private Long workdayId;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "activity")
    private String activity;

    @Column(name = "planned_hours", nullable = false)
    private Integer plannedHours;

    @Column(name = "filling_date", nullable = false)
    private LocalDate fillingDate;

    @Column(name = "planned_date", nullable = false)
    private LocalDate plannedDate;

    @Column(name = "status", nullable = false)
    private String status;

    protected PlannedOvertimeDao() {}

    public PlannedOvertimeDao(Long employeeId, Long workdayId, Long managerId, String activity,
                              Integer plannedHours, LocalDate fillingDate, LocalDate plannedDate, String status) {
        this.employeeId = employeeId;
        this.workdayId = workdayId;
        this.managerId = managerId;
        this.activity = activity;
        this.plannedHours = plannedHours;
        this.fillingDate = fillingDate;
        this.plannedDate = plannedDate;
        this.status = status;
    }

    public Long getPoId() { return poId; }
    public Long getEmployeeId() { return employeeId; }
    public Long getWorkdayId() { return workdayId; }
    public Long getManagerId() { return managerId; }
    public String getActivity() { return activity; }
    public Integer getPlannedHours() { return plannedHours; }
    public LocalDate getFillingDate() { return fillingDate; }
    public LocalDate getPlannedDate() { return plannedDate; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}
