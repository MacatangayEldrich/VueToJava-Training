package com.example.backend.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "manager")
public class ManagerDao {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "manager_name", nullable = false)
    private String managerName;

    protected ManagerDao() {}

    public ManagerDao(Long managerId, Long employeeId, String managerName) {
        this.managerId = managerId;
        this.employeeId = employeeId;
        this.managerName = managerName;
    }

    public Long getManagerId() { return managerId; }
    public Long getEmployeeId() { return employeeId; }
    public String getManagerName() { return managerName; }
}
