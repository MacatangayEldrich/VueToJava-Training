package com.example.backend.dto;

import java.time.LocalDate;

public record PlannedOvertimeDto(
        Long poId,
        Long employeeId,
        Long workdayId,
        Long managerId,
        String activity,
        Integer plannedHours,
        LocalDate fillingDate,
        LocalDate plannedDate,
        String status) {}
