package com.example.backend.service.impl;

import com.example.backend.dto.ApprovalListDto;
import com.example.backend.repository.ApprovalListRepository;
import com.example.backend.repository.ManagerRepository;
import com.example.backend.service.ApprovalListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalListServiceImpl implements ApprovalListService {

    private final ApprovalListRepository repository;
    private final ManagerRepository managerRepository;

    public ApprovalListServiceImpl(ApprovalListRepository repository, ManagerRepository managerRepository) {
        this.repository = repository;
        this.managerRepository = managerRepository;
    }

    @Override
    public List<ApprovalListDto> findAllByManagerId(Long employeeId) {
        return repository.findAllByManagerId(getManagerByEmployeeId(employeeId));
    }

    private Long getManagerByEmployeeId(Long employeeId) {
        return managerRepository.findByEmployeeId(employeeId).getManagerId() == null ? '0' : managerRepository.findByEmployeeId(employeeId).getManagerId();

    }
}
