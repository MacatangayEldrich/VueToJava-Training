package com.example.backend.repository;

import com.example.backend.dao.WorkdayTypeDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkdayTypeRepository extends JpaRepository<WorkdayTypeDao, Long> {
}
