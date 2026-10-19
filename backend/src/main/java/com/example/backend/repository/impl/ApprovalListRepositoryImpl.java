package com.example.backend.repository.impl;

import com.example.backend.dto.ApprovalListDto;
import com.example.backend.repository.ApprovalListRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ApprovalListRepositoryImpl implements ApprovalListRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ApprovalListDto> findAll() {
        String sql = """
            SELECT
                p.PO_ID AS REQUEST_ID, 
                'Overtime Request' AS TYPE, 
                e.EMPLOYEE_NAME, 
                p.FILLING_DATE, 
                p.STATUS
            FROM PLANNED_OVERTIME p
            INNER JOIN EMPLOYEE e
                ON p.EMPLOYEE_ID = e.EMPLOYEE_ID

            UNION ALL
            
            SELECT 
                a.AO_ID AS REQUEST_ID, 
                'Actual Overtime' AS TYPE, 
                e.EMPLOYEE_NAME, 
                a.FILLING_DATE,  
                a.STATUS
            FROM ACTUAL_OVERTIME a
            INNER JOIN PLANNED_OVERTIME p
                ON p.PO_ID = a.PO_ID
            INNER JOIN EMPLOYEE e
                ON p.EMPLOYEE_ID = e.EMPLOYEE_ID

            ORDER BY FILLING_DATE;
            """;

        Query query = entityManager.createNativeQuery(sql);

        @SuppressWarnings("unchecked")
        List<Object[]> rows = query.getResultList();

        return rows.stream()
                .map(row -> new ApprovalListDto(
                    ((Number) row[0]).longValue(),
                    (String) row[1],
                    (String) row[2],
                    ((Date) row[3]).toLocalDate(),
                    (String) row[4]))
                .toList();
    }
}