package com.example.backend.service;

import com.example.backend.dto.ActualOvertimeDto;
import com.example.backend.dto.ActualOvertimeDtoExtended;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActualOvertimeService extends CrudService<ActualOvertimeDto> {

    ActualOvertimeDto approve(Long id, Long employeeId);

    ActualOvertimeDto reject(Long id, Long employeeId);

    Page<ActualOvertimeDtoExtended> findByEmployeeId(Long employeeId, Pageable pageable);
}
