package com.example.backend.repository;

import com.example.backend.dto.ApprovalListDto;

import java.util.List;

public interface ApprovalListRepository {
    List<ApprovalListDto> findAllByManagerId(Long managerId);
}