package com.example.backend.repository;

import com.example.backend.dao.PlannedOvertimeDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannedOvertimeRepository extends JpaRepository<PlannedOvertimeDao, Long> {
    List<PlannedOvertimeDao> findByEmployeeId(Long employeeId);
}
