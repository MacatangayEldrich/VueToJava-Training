package com.example.backend.controller;

import com.example.backend.dto.ActualOvertimeDto;
import com.example.backend.service.ActualOvertimeService;
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
@RequestMapping("/v1/actual-overtimes")
public class ActualOvertimeController {
    private final ActualOvertimeService service;

    public ActualOvertimeController(ActualOvertimeService service) {
        this.service = service;
    }

    @GetMapping
    public List<ActualOvertimeDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ActualOvertimeDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActualOvertimeDto create(@RequestBody ActualOvertimeDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ActualOvertimeDto update(@PathVariable Long id, @RequestBody ActualOvertimeDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
