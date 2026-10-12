package com.example.backend.controller;

import com.example.backend.dto.WorkdayTypeDto;
import com.example.backend.service.WorkdayTypeService;
import java.util.List;
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
@RequestMapping("/v1/workday-types")
public class WorkdayTypeController {
    private final WorkdayTypeService service;

    public WorkdayTypeController(WorkdayTypeService service) {
        this.service = service;
    }

    @GetMapping
    public List<WorkdayTypeDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public WorkdayTypeDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkdayTypeDto create(@RequestBody WorkdayTypeDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public WorkdayTypeDto update(@PathVariable Long id, @RequestBody WorkdayTypeDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
