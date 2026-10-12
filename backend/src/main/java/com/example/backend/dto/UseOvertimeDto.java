package com.example.backend.dto;

import java.time.LocalDate;

public record UseOvertimeDto(
        Long uoId,
        Long managerId,
        String uoStatus,
        Integer useHours,
        LocalDate fillingDate,
        LocalDate useDate,
        String remarks) {}
