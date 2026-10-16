package com.example.backend.dto;

import java.time.LocalDate;

public record ApprovalListDto(
    Long requestId, 
    String requestType,
    String employeeName, 
    LocalDate fillingDate,
    String status) {}
