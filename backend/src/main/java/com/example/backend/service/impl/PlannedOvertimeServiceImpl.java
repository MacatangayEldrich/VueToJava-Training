package com.example.backend.service.impl;

import com.example.backend.dao.PlannedOvertimeDao;
import com.example.backend.dto.PlannedOvertimeDto;
import com.example.backend.repository.ManagerRepository;
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
    private ManagerRepository managerRepository;

    public PlannedOvertimeServiceImpl(PlannedOvertimeRepository repository, ManagerRepository managerRepository) {
        super(repository);
        this.repository = repository;
        this.managerRepository = managerRepository;
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

    public PlannedOvertimeDto approve(Long id, Long employeeId) {
        PlannedOvertimeDao entity = repository.findById(id).orElseThrow();
        entity.setManagerId(getManagerByEmployeeId(employeeId));
        entity.setStatus("APPROVED");
        
        return toDto(repository.save(entity));
    }

    public PlannedOvertimeDto reject(Long id, Long employeeId) {
        PlannedOvertimeDao entity = repository.findById(id).orElseThrow();
        entity.setManagerId(getManagerByEmployeeId(employeeId));
        entity.setStatus("REJECTED");
        

        return toDto(repository.save(entity));
    }

    private Long getManagerByEmployeeId(Long employeeId) {
        return managerRepository.findByEmployeeId(employeeId).getManagerId() == null ? '0' : managerRepository.findByEmployeeId(employeeId).getManagerId();

    }
}