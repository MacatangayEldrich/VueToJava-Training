package com.example.backend.controller;

import com.example.backend.dto.UseOvertimeDto;
import com.example.backend.service.UseOvertimeService;
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
@RequestMapping("/v1/use-overtimes")
public class UseOvertimeController {
    private final UseOvertimeService service;

    public UseOvertimeController(UseOvertimeService service) {
        this.service = service;
    }

    @GetMapping
    public List<UseOvertimeDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UseOvertimeDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UseOvertimeDto create(@RequestBody UseOvertimeDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public UseOvertimeDto update(@PathVariable Long id, @RequestBody UseOvertimeDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
