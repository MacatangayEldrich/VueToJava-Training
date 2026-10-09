package com.example.backend.repository;

import com.example.backend.dao.PlannedOvertimeDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlannedOvertimeRepository extends JpaRepository<PlannedOvertimeDao, Long> {}
