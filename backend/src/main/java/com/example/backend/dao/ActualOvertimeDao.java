package com.example.backend.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "actual_overtime")
public class ActualOvertimeDao {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ao_id")
    private Long aoId;

    @Column(name = "po_id", nullable = false)
    private Long poId;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "activity")
    private String activity;

    @Column(name = "rendered_hours", nullable = false)
    private Integer renderedHours;

    @Column(name = "earned_hours", nullable = false, precision = 10, scale = 2)
    private BigDecimal earnedHours;

    @Column(name = "filling_date", nullable = false)
    private LocalDate fillingDate;

    @Column(name = "actual_date", nullable = false)
    private LocalDate actualDate;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "remarks", nullable = false)
    private String remarks;

    protected ActualOvertimeDao() {
    }

    public ActualOvertimeDao(Long poId, Long managerId, String status, String activity,
            Integer renderedHours, BigDecimal earnedHours, LocalDate fillingDate,
            LocalDate actualDate, LocalDate expirationDate, String remarks) {
        this.poId = poId;
        this.managerId = managerId;
        this.status = status;
        this.activity = activity;
        this.renderedHours = renderedHours;
        this.earnedHours = earnedHours;
        this.fillingDate = fillingDate;
        this.actualDate = actualDate;
        this.expirationDate = expirationDate;
        this.remarks = remarks;
    }

    public Long getAoId() {
        return aoId;
    }

    public Long getPoId() {
        return poId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public String getStatus() {
        return status;
    }

    public String getActivity() {
        return activity;
    }

    public Integer getRenderedHours() {
        return renderedHours;
    }

    public BigDecimal getEarnedHours() {
        return earnedHours;
    }

    public LocalDate getFillingDate() {
        return fillingDate;
    }

    public LocalDate getActualDate() {
        return actualDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
