package com.example.backend.service;

import com.example.backend.dto.ApprovalListDto;
import com.example.backend.dto.PlannedOvertimeDto;

import java.util.List;

public interface ApprovalListService {
    List<ApprovalListDto> findAll();

    List<PlannedOvertimeDto> findActualPlannedOTByEmployeeId(Long employeeId);
}
