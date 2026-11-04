package com.example.backend.dto;

import java.math.BigDecimal;
import java.sql.Date;

public record ActualOvertimeDtoExtended(
        Long aoId,
        Long poId,
        Long managerId,
        String status,
        String activity,
        Integer renderedHours,
        BigDecimal earnedHours,
        Date fillingDate,
        Date actualDate,
        Date expirationDate,
        String remarks,
        Long workdayId, 
        String workdayName, 
        BigDecimal factorRate
) {}
        