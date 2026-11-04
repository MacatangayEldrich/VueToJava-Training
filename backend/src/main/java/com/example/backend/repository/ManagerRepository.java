package com.example.backend.repository;

import com.example.backend.dao.ManagerDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<ManagerDao, Long> {
    ManagerDao findByEmployeeId(Long employeeId);
}
