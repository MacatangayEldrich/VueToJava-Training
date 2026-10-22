package com.example.backend.service.impl;

import com.example.backend.dao.PlannedOvertimeDao;
import com.example.backend.dto.PlannedOvertimeDto;
import com.example.backend.repository.PlannedOvertimeRepository;
import com.example.backend.service.PlannedOvertimeService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlannedOvertimeServiceImpl extends AbstractCrudService<PlannedOvertimeDao, PlannedOvertimeDto> implements PlannedOvertimeService {
    private final PlannedOvertimeRepository repository;
    public PlannedOvertimeServiceImpl(PlannedOvertimeRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Page<PlannedOvertimeDto> findByEmployeeId(Long employeeId, Pageable pageable) {
        return repository.findByEmployeeId(employeeId, pageable).map(this::toDto);
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

    public PlannedOvertimeDto approve(Long id) {
        PlannedOvertimeDao entity = repository.findById(id).orElseThrow();

        entity.setStatus("APPROVED");

        return toDto(repository.save(entity));
    }

    public PlannedOvertimeDto reject(Long id) {
        PlannedOvertimeDao entity = repository.findById(id).orElseThrow();

        entity.setStatus("REJECTED");

        return toDto(repository.save(entity));
    }    

    @Override
    public List<PlannedOvertimeDto> findActualPlannedOT() {
        return repository.findActualPlannedOT().stream().map(this::toDto).collect(Collectors.toList());
    }
}