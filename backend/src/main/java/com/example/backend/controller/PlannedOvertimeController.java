package com.example.backend.controller;

import com.example.backend.dto.PlannedOvertimeDto;
import com.example.backend.service.PlannedOvertimeService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/planned-overtimes")
public class PlannedOvertimeController {
    private final PlannedOvertimeService service;

    public PlannedOvertimeController(PlannedOvertimeService service) {
        this.service = service;
    }

    @GetMapping
    public List<PlannedOvertimeDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PlannedOvertimeDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/employee/{id}")
    public Page<PlannedOvertimeDto> findByEmployeeId(
            @PathVariable Long id,
            @PageableDefault(size = 5, sort = {"plannedDate", "poId"}) Pageable pageable) {
        return service.findByEmployeeId(id, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlannedOvertimeDto create(@RequestBody PlannedOvertimeDto dto) {
        System.out.println(dto);
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public PlannedOvertimeDto update(@PathVariable Long id, @RequestBody PlannedOvertimeDto dto) {
        return service.update(id, dto);
    }

    @PutMapping("/{id}/approve")
    public PlannedOvertimeDto approve(@PathVariable Long id) {
        return service.approve(id);
    }

    @PutMapping("/{id}/reject")
    public PlannedOvertimeDto reject(@PathVariable Long id) {
        return service.reject(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }   

    @GetMapping("/getActualPlannedOvertimes")
    public List<PlannedOvertimeDto> findActualPlannedOT() {
        return service.findActualPlannedOT();
    }
}
