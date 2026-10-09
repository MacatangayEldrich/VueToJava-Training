package com.example.backend.service.impl;

import com.example.backend.dao.UseOvertimeDao;
import com.example.backend.dto.UseOvertimeDto;
import com.example.backend.repository.UseOvertimeRepository;
import com.example.backend.service.UseOvertimeService;
import org.springframework.stereotype.Service;

@Service
public class UseOvertimeServiceImpl extends AbstractCrudService<UseOvertimeDao, UseOvertimeDto> implements UseOvertimeService {
    public UseOvertimeServiceImpl(UseOvertimeRepository repository) {
        super(repository);
    }

    protected Long idOf(UseOvertimeDto dto) { return dto.uoId(); }
    protected UseOvertimeDao toEntity(UseOvertimeDto dto) {
        return new UseOvertimeDao(dto.uoId(), dto.managerId(), dto.uoStatus(), dto.useHours(),
                dto.fillingDate(), dto.useDate(), dto.remarks());
    }
    protected UseOvertimeDto toDto(UseOvertimeDao entity) {
        return new UseOvertimeDto(entity.getUoId(), entity.getManagerId(), entity.getUoStatus(),
                entity.getUseHours(), entity.getFillingDate(), entity.getUseDate(), entity.getRemarks());
    }
}
