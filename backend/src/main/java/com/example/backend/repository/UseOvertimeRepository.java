package com.example.backend.repository;

import com.example.backend.dao.UseOvertimeDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UseOvertimeRepository extends JpaRepository<UseOvertimeDao, Long> {}
