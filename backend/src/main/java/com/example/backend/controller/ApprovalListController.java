package com.example.backend.controller;

import com.example.backend.dto.ApprovalListDto;
import com.example.backend.service.ApprovalListService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/approval-list")
public class ApprovalListController {
    private final ApprovalListService service;

    public ApprovalListController(ApprovalListService service) {
        this.service = service;
    }

    @GetMapping("/manager/{managerId}")
    public List<ApprovalListDto> findAllByManagerId(@PathVariable Long managerId) {
        System.out.println("findAllByManagerId: " + managerId);
        return service.findAllByManagerId(managerId);
    }
}
