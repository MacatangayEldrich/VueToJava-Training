package com.example.backend.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class EmployeeDao {
    @Id
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    private String department;
    private String position;

    @Column(name = "work_days")
    private String workDays;

    @Column(name = "work_hours")
    private String workHours;

    protected EmployeeDao() {}

    public EmployeeDao(Long employeeId, String employeeName, String department, String position, String workDays, String workHours) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
        this.position = position;
        this.workDays = workDays;
        this.workHours = workHours;
    }

    public Long getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }
    public String getWorkDays() { return workDays; }
    public String getWorkHours() { return workHours; }
}
