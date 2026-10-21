package com.example.backend.repository;

import com.example.backend.dto.ApprovalListDto;
import com.example.backend.dto.PlannedOvertimeDto;
import java.util.List;

public interface ApprovalListRepository {
    List<ApprovalListDto> findAllByManagerId(Long managerId);

    List<PlannedOvertimeDto> findActualPlannedOTByEmployeeId(Long employeeId);
}