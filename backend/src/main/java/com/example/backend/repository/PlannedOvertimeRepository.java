package com.example.backend.repository;

import com.example.backend.dao.PlannedOvertimeDao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlannedOvertimeRepository extends JpaRepository<PlannedOvertimeDao, Long> {
    Page<PlannedOvertimeDao> findByEmployeeId(Long employeeId, Pageable pageable);


}
