package com.example.backend.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "manager")
public class ManagerDao {
    @Id
    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "manager_name", nullable = false)
    private String managerName;

    protected ManagerDao() {}

    public ManagerDao(Long managerId, String managerName) {
        this.managerId = managerId;
        this.managerName = managerName;
    }

    public Long getManagerId() { return managerId; }
    public String getManagerName() { return managerName; }
}
