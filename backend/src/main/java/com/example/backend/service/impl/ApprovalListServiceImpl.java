package com.example.backend.service.impl;

import com.example.backend.dto.ApprovalListDto;
import com.example.backend.dto.PlannedOvertimeDto;
import com.example.backend.repository.ApprovalListRepository;
import com.example.backend.service.ApprovalListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalListServiceImpl implements ApprovalListService {

    private final ApprovalListRepository repository;

    public ApprovalListServiceImpl(ApprovalListRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ApprovalListDto> findAllByManagerId(Long managerId) {
        return repository.findAllByManagerId(managerId);
    }

    @Override
    public List<PlannedOvertimeDto> findActualPlannedOTByEmployeeId(Long employeeId) {
        return repository.findActualPlannedOTByEmployeeId(employeeId);
    }
}
