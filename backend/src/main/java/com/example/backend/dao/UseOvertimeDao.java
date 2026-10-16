package com.example.backend.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "use_overtime")
public class UseOvertimeDao {
    @Id
    @Column(name = "uo_id")
    private Long uoId;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "use_hours", nullable = false)
    private Integer useHours;

    @Column(name = "filling_date", nullable = false)
    private LocalDate fillingDate;

    @Column(name = "use_date", nullable = false)
    private LocalDate useDate;

    @Column(name = "remarks", nullable = false)
    private String remarks;

    protected UseOvertimeDao() {}

    public UseOvertimeDao(Long uoId, Long employeeId, Long managerId, String status, Integer useHours,
                          LocalDate fillingDate, LocalDate useDate, String remarks) {
        this.uoId = uoId;
        this.employeeId = employeeId;
        this.managerId = managerId;
        this.status = status;
        this.useHours = useHours;
        this.fillingDate = fillingDate;
        this.useDate = useDate;
        this.remarks = remarks;
    }

    public Long getUoId() { return uoId; }
    public Long getEmployeeId() { return employeeId; }
    public Long getManagerId() { return managerId; }
    public String getStatus() { return status; }
    public Integer getUseHours() { return useHours; }
    public LocalDate getFillingDate() { return fillingDate; }
    public LocalDate getUseDate() { return useDate; }
    public String getRemarks() { return remarks; }
}
