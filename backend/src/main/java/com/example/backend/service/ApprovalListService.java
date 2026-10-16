package com.example.backend.service;

import com.example.backend.dto.ApprovalListDto;

import java.util.List;

public interface ApprovalListService {
    List<ApprovalListDto> findAll();
}
