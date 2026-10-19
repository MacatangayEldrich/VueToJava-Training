package com.example.backend.dto;

import java.time.LocalDate;

public record UseOvertimeDto(
        Long uoId,
        Long employeeId,
        Long managerId,
        String status,
        Integer useHours,
        LocalDate fillingDate,
        LocalDate useDate,
        String remarks) {}
        