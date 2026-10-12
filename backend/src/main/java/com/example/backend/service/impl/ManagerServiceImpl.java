package com.example.backend.service.impl;

import com.example.backend.dao.ManagerDao;
import com.example.backend.dto.ManagerDto;
import com.example.backend.repository.ManagerRepository;
import com.example.backend.service.ManagerService;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl extends AbstractCrudService<ManagerDao, ManagerDto> implements ManagerService {
    public ManagerServiceImpl(ManagerRepository repository) {
        super(repository);
    }

    protected Long idOf(ManagerDto dto) { return dto.managerId(); }
    protected ManagerDao toEntity(ManagerDto dto) { return new ManagerDao(dto.managerId(), dto.managerName()); }
    protected ManagerDto toDto(ManagerDao entity) { return new ManagerDto(entity.getManagerId(), entity.getManagerName()); }
}
