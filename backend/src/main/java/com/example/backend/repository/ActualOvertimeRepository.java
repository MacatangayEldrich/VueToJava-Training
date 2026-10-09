package com.example.backend.repository;

import com.example.backend.dao.ActualOvertimeDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualOvertimeRepository extends JpaRepository<ActualOvertimeDao, Long> {}
