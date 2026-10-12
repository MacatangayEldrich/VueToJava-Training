package com.example.backend.service.impl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Transactional
public abstract class AbstractCrudService<E, D> {
    private final JpaRepository<E, Long> repository;

    protected AbstractCrudService(JpaRepository<E, Long> repository) {
        this.repository = repository;
    }

    protected abstract Long idOf(D dto);
    protected abstract E toEntity(D dto);
    protected abstract D toDto(E entity);

    public List<D> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public D findById(Long id) {
        return toDto(repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public D create(D dto) {
        Long id = idOf(dto);
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An ID is required by the database schema");
        }
        if (repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "An entity with this ID already exists");
        }
        return toDto(repository.save(toEntity(dto)));
    }

    public D update(Long id, D dto) {
        if (!id.equals(idOf(dto))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The path ID must match the DTO ID");
        }
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return toDto(repository.save(toEntity(dto)));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }
}
