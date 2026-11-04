package com.example.backend.dto;

import java.math.BigDecimal;

public record WorkdayTypeDto(Long workdayId, String workdayName, BigDecimal factorRate) {}
