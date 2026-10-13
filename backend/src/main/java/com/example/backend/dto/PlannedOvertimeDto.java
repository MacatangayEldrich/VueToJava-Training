package com.example.backend.dto;

import java.time.LocalDate;

public record PlannedOvertimeDto(
        Long poId,
        Long employeeId,
        Long workdayId,
        Long managerId,
        String poDescription,
        Integer plannedHours,
        LocalDate filingDate,
        LocalDate plannedDate,
        String poStatus) {}
