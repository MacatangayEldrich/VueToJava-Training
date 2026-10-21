package com.example.backend.service;

import com.example.backend.dto.ActualOvertimeDto;

public interface ActualOvertimeService extends CrudService<ActualOvertimeDto> {

    ActualOvertimeDto approve(Long id);

    ActualOvertimeDto reject(Long id);

}
