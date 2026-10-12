package com.example.backend.repository;

import com.example.backend.dao.EmployeeDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeDao, Long> {}
