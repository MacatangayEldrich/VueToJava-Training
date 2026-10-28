package com.example.backend.dto;

import java.time.LocalDate;

public record ApprovalListDto(
    Long requestId, 
    String requestType,
    Number employeeId,
    String employeeName, 
    LocalDate fillingDate,
    String status) {}
