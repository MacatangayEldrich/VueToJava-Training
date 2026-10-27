package com.example.backend.service.impl;

import com.example.backend.dao.ActualOvertimeDao;
import com.example.backend.dto.ActualOvertimeDto;
import com.example.backend.repository.ActualOvertimeRepository;
import com.example.backend.repository.ManagerRepository;
import com.example.backend.service.ActualOvertimeService;
import org.springframework.stereotype.Service;

@Service
public class ActualOvertimeServiceImpl extends AbstractCrudService<ActualOvertimeDao, ActualOvertimeDto> implements ActualOvertimeService {
    private final ActualOvertimeRepository repository;
    private final ManagerRepository managerRepository;

    public ActualOvertimeServiceImpl(ActualOvertimeRepository repository, ManagerRepository managerRepository) {
        super(repository);
        this.repository = repository;
        this.managerRepository = managerRepository;
    }

    protected Long idOf(ActualOvertimeDto dto) { return dto.aoId(); }
    protected ActualOvertimeDao toEntity(ActualOvertimeDto dto) {
        return new ActualOvertimeDao(dto.aoId(), dto.poId(), dto.managerId(), dto.status(), dto.activity(),
                dto.renderedHours(), dto.earnedHours(), dto.fillingDate(), dto.actualDate(),
                dto.expirationDate(), dto.remarks());
    }
    protected ActualOvertimeDto toDto(ActualOvertimeDao entity) {
        return new ActualOvertimeDto(entity.getAoId(), entity.getPoId(), entity.getManagerId(), entity.getStatus(),
                entity.getActivity(), entity.getRenderedHours(), entity.getEarnedHours(), entity.getFillingDate(),
                entity.getActualDate(), entity.getExpirationDate(), entity.getRemarks());
    }

    public ActualOvertimeDto approve(Long id, Long employeeId) {
        ActualOvertimeDao entity = repository.findById(id).orElseThrow();
        entity.setManagerId(getManagerByEmployeeId(employeeId));
        entity.setStatus("APPROVED");
        
        return toDto(repository.save(entity));
    }

    public ActualOvertimeDto reject(Long id, Long employeeId) {
        ActualOvertimeDao entity = repository.findById(id).orElseThrow();
        entity.setManagerId(getManagerByEmployeeId(employeeId));
        entity.setStatus("REJECTED");
        
        return toDto(repository.save(entity));
    }

    private Long getManagerByEmployeeId(Long employeeId) {
        return managerRepository.findByEmployeeId(employeeId).getManagerId() == null ? '0' : managerRepository.findByEmployeeId(employeeId).getManagerId();

    }
}