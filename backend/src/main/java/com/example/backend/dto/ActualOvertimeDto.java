package com.example.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ActualOvertimeDto(
        Long aoId,
        Long poId,
        Long managerId,
        String aoStatus,
        String aoDescription,
        Integer renderedHours,
        BigDecimal earnedHours,
        LocalDate fillingDate,
        LocalDate actualDate,
        LocalDate expirationDate,
        String remarks) {}
