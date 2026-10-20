package com.example.backend.service;

import com.example.backend.dto.PlannedOvertimeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlannedOvertimeService extends CrudService<PlannedOvertimeDto> {

    PlannedOvertimeDto approve(Long id);

    PlannedOvertimeDto reject(Long id);
    
    Page<PlannedOvertimeDto> findByEmployeeId(Long employeeId, Pageable pageable);
}
