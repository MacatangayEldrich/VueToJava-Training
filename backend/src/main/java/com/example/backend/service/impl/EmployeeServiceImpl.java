package com.example.backend.service.impl;

import com.example.backend.dao.EmployeeDao;
import com.example.backend.dto.EmployeeDto;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends AbstractCrudService<EmployeeDao, EmployeeDto> implements EmployeeService {
    public EmployeeServiceImpl(EmployeeRepository repository) {
        super(repository);
    }

    protected Long idOf(EmployeeDto dto) { return dto.employeeId(); }
    protected EmployeeDao toEntity(EmployeeDto dto) {
        return new EmployeeDao(dto.employeeId(), dto.employeeName(), dto.department(), dto.position(), dto.workDays(), dto.workHours());
    }
    protected EmployeeDto toDto(EmployeeDao entity) {
        return new EmployeeDto(entity.getEmployeeId(), entity.getEmployeeName(), entity.getDepartment(),
                entity.getPosition(), entity.getWorkDays(), entity.getWorkHours());
    }
}
