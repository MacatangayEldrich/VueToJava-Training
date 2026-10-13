package com.example.backend.service;

import java.util.List;

import com.example.backend.dto.PlannedOvertimeDto;

public interface PlannedOvertimeService extends CrudService<PlannedOvertimeDto> {
    public List<PlannedOvertimeDto> findByEmployeeId(Long employeeId);
}
