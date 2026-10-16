package com.example.backend.service;

import com.example.backend.dto.PlannedOvertimeDto;

public interface PlannedOvertimeService extends CrudService<PlannedOvertimeDto> {

    PlannedOvertimeDto approve(Long id);

    PlannedOvertimeDto reject(Long id);
}
