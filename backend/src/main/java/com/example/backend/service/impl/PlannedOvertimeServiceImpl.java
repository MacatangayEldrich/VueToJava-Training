package com.example.backend.service.impl;

import com.example.backend.dao.PlannedOvertimeDao;
import com.example.backend.dto.PlannedOvertimeDto;
import com.example.backend.repository.PlannedOvertimeRepository;
import com.example.backend.service.PlannedOvertimeService;
import org.springframework.stereotype.Service;

@Service
public class PlannedOvertimeServiceImpl extends AbstractCrudService<PlannedOvertimeDao, PlannedOvertimeDto> implements PlannedOvertimeService {
    private final PlannedOvertimeRepository repository;

    public PlannedOvertimeServiceImpl(PlannedOvertimeRepository repository) {
        super(repository);
        this.repository = repository;
    }

    protected Long idOf(PlannedOvertimeDto dto) { return dto.poId(); }
    protected PlannedOvertimeDao toEntity(PlannedOvertimeDto dto) {
        return new PlannedOvertimeDao(dto.poId(), dto.employeeId(), dto.workdayId(), dto.managerId(),
                dto.otDescription(), dto.plannedHours(), dto.fillingDate(), dto.plannedDate(), dto.poStatus());
    }
    protected PlannedOvertimeDto toDto(PlannedOvertimeDao entity) {
        return new PlannedOvertimeDto(entity.getPoId(), entity.getEmployeeId(), entity.getWorkdayId(),
                entity.getManagerId(), entity.getOtDescription(), entity.getPlannedHours(),
                entity.getFillingDate(), entity.getPlannedDate(), entity.getPoStatus());
    }

    public PlannedOvertimeDto approve(Long id) {
        PlannedOvertimeDao entity = repository.findById(id).orElseThrow();

        entity.setPoStatus("APPROVED");

        return toDto(repository.save(entity));
    }

    public PlannedOvertimeDto reject(Long id) {
        PlannedOvertimeDao entity = repository.findById(id).orElseThrow();

        entity.setPoStatus("REJECTED");

        return toDto(repository.save(entity));
    }
}
