package com.example.backend.service.impl;

import com.example.backend.dao.PlannedOvertimeDao;
import com.example.backend.dto.PlannedOvertimeDto;
import com.example.backend.repository.PlannedOvertimeRepository;
import com.example.backend.service.PlannedOvertimeService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PlannedOvertimeServiceImpl extends AbstractCrudService<PlannedOvertimeDao, PlannedOvertimeDto> implements PlannedOvertimeService {
    private final PlannedOvertimeRepository repository;
    public PlannedOvertimeServiceImpl(PlannedOvertimeRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<PlannedOvertimeDto> findByEmployeeId(Long employeeId){
        return this.repository.findByEmployeeId(employeeId).stream().map(this::toDto).toList();
    }

    protected Long idOf(PlannedOvertimeDto dto) { return dto.poId(); }
    protected PlannedOvertimeDao toEntity(PlannedOvertimeDto dto) {
        return new PlannedOvertimeDao(dto.employeeId(), dto.workdayId(), dto.managerId(),
                dto.activity(), dto.plannedHours(), dto.fillingDate(), dto.plannedDate(), dto.status());
    }
    protected PlannedOvertimeDto toDto(PlannedOvertimeDao entity) {
        return new PlannedOvertimeDto(entity.getPoId(), entity.getEmployeeId(), entity.getWorkdayId(),
                entity.getManagerId(), entity.getActivity(), entity.getPlannedHours(),
                entity.getFillingDate(), entity.getPlannedDate(), entity.getStatus());
    }

}
