package com.example.backend.service.impl;

import com.example.backend.dao.WorkdayTypeDao;
import com.example.backend.dto.WorkdayTypeDto;
import com.example.backend.repository.WorkdayTypeRepository;
import com.example.backend.service.WorkdayTypeService;
import org.springframework.stereotype.Service;

@Service
public class WorkdayTypeServiceImpl extends AbstractCrudService<WorkdayTypeDao, WorkdayTypeDto>
        implements WorkdayTypeService {
    public WorkdayTypeServiceImpl(WorkdayTypeRepository repository) {
        super(repository);
    }

    protected Long idOf(WorkdayTypeDto dto) {
        return dto.workdayId();
    }

    protected WorkdayTypeDao toEntity(WorkdayTypeDto dto) {
        return new WorkdayTypeDao(dto.workdayId(), dto.workdayName(), dto.factorRate());
    }

    protected WorkdayTypeDto toDto(WorkdayTypeDao entity) {
        return new WorkdayTypeDto(entity.getWorkdayId(), entity.getWorkdayName(), entity.getFactorRate());
    }
}
