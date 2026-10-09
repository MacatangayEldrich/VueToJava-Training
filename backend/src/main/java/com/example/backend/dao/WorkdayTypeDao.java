package com.example.backend.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "workday_type")
public class WorkdayTypeDao {
    @Id
    @Column(name = "workday_id")
    private Long workdayId;

    @Column(name = "workday_name")
    private String workdayName;

    @Column(name = "factor_rate", precision = 10, scale = 2)
    private BigDecimal factorRate;

    protected WorkdayTypeDao() {}

    public WorkdayTypeDao(Long workdayId, String workdayName, BigDecimal factorRate) {
        this.workdayId = workdayId;
        this.workdayName = workdayName;
        this.factorRate = factorRate;
    }

    public Long getWorkdayId() { return workdayId; }
    public String getWorkdayName() { return workdayName; }
    public BigDecimal getFactorRate() { return factorRate; }
}
