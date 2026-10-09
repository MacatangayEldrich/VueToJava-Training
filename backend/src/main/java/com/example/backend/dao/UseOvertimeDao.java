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

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "uo_status", nullable = false)
    private String uoStatus;

    @Column(name = "use_hours", nullable = false)
    private Integer useHours;

    @Column(name = "filling_date", nullable = false)
    private LocalDate fillingDate;

    @Column(name = "use_date", nullable = false)
    private LocalDate useDate;

    @Column(name = "remarks", nullable = false)
    private String remarks;

    protected UseOvertimeDao() {}

    public UseOvertimeDao(Long uoId, Long managerId, String uoStatus, Integer useHours,
                          LocalDate fillingDate, LocalDate useDate, String remarks) {
        this.uoId = uoId;
        this.managerId = managerId;
        this.uoStatus = uoStatus;
        this.useHours = useHours;
        this.fillingDate = fillingDate;
        this.useDate = useDate;
        this.remarks = remarks;
    }

    public Long getUoId() { return uoId; }
    public Long getManagerId() { return managerId; }
    public String getUoStatus() { return uoStatus; }
    public Integer getUseHours() { return useHours; }
    public LocalDate getFillingDate() { return fillingDate; }
    public LocalDate getUseDate() { return useDate; }
    public String getRemarks() { return remarks; }
}
