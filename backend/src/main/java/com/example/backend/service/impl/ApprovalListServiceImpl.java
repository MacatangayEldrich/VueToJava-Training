package com.example.backend.service.impl;

import com.example.backend.dto.ApprovalListDto;
import com.example.backend.dto.PlannedOvertimeDto;
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
    public List<ApprovalListDto> findAllByManagerEmployeeId(Long employeeId) {
        return repository.findAllByManagerEmployeeId(employeeId);
    }

    @Override
    public List<PlannedOvertimeDto> findActualPlannedOTByEmployeeId(Long employeeId) {
        return repository.findActualPlannedOTByEmployeeId(employeeId);
    }
}
