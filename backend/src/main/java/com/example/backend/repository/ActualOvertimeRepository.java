package com.example.backend.repository;

import com.example.backend.dao.ActualOvertimeDao;
import com.example.backend.dto.ActualOvertimeDtoExtended;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActualOvertimeRepository extends JpaRepository<ActualOvertimeDao, Long> {

    @Query(
        value = """
            SELECT
                AO.AO_ID as aoId,
                AO.PO_ID as poId,
                AO.MANAGER_ID as managerId,
                AO.STATUS as status,
                AO.ACTIVITY as activity,
                AO.RENDERED_HOURS as renderedHours,
                AO.EARNED_HOURS as earnedHours,
                AO.FILLING_DATE as fillingDate,
                AO.ACTUAL_DATE as actualDate,
                AO.EXPIRATION_DATE as expirationDate,
                AO.REMARKS as remarks,
                WT.WORKDAY_ID as workdayId,
                WT.WORKDAY_NAME as workDayName,
                WT.FACTOR_RATE as factorRate
             FROM ACTUAL_OVERTIME AO
                INNER JOIN PLANNED_OVERTIME PO
                    ON AO.PO_ID = PO.PO_ID
                INNER JOIN WORKDAY_TYPE WT
                    ON WT.WORKDAY_ID = PO.WORKDAY_ID
            WHERE PO.EMPLOYEE_ID = :employeeId
                
        """,
        countQuery = """
            SELECT COUNT(*)
             FROM ACTUAL_OVERTIME AO
                INNER JOIN PLANNED_OVERTIME PO
                    ON AO.PO_ID = PO.PO_ID
                INNER JOIN WORKDAY_TYPE WT
                    ON WT.WORKDAY_ID = PO.WORKDAY_ID
            WHERE PO.EMPLOYEE_ID = :employeeId
        """,
        nativeQuery=true
    )
    Page<ActualOvertimeDtoExtended> findByEmployeeId( Long employeeId, Pageable pageable);
}
