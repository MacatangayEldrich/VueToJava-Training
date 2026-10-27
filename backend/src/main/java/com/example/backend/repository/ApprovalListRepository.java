package com.example.backend.repository;

import com.example.backend.dto.ApprovalListDto;
import com.example.backend.dto.PlannedOvertimeDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ApprovalListRepository {
    List<ApprovalListDto> findAllByManagerId(Long managerId);
    List<ApprovalListDto> findAll();

    List<PlannedOvertimeDto> findActualPlannedOTByEmployeeId(Long employeeId);
}