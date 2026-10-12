package com.example.backend.service.impl;

import com.example.backend.dao.ActualOvertimeDao;
import com.example.backend.dto.ActualOvertimeDto;
import com.example.backend.repository.ActualOvertimeRepository;
import com.example.backend.service.ActualOvertimeService;
import org.springframework.stereotype.Service;

@Service
public class ActualOvertimeServiceImpl extends AbstractCrudService<ActualOvertimeDao, ActualOvertimeDto> implements ActualOvertimeService {
    public ActualOvertimeServiceImpl(ActualOvertimeRepository repository) {
        super(repository);
    }

    protected Long idOf(ActualOvertimeDto dto) { return dto.aoId(); }
    protected ActualOvertimeDao toEntity(ActualOvertimeDto dto) {
        return new ActualOvertimeDao(dto.aoId(), dto.poId(), dto.managerId(), dto.aoStatus(), dto.aoDescription(),
                dto.renderedHours(), dto.earnedHours(), dto.fillingDate(), dto.actualDate(),
                dto.expirationDate(), dto.remarks());
    }
    protected ActualOvertimeDto toDto(ActualOvertimeDao entity) {
        return new ActualOvertimeDto(entity.getAoId(), entity.getPoId(), entity.getManagerId(), entity.getAoStatus(),
                entity.getAoDescription(), entity.getRenderedHours(), entity.getEarnedHours(), entity.getFillingDate(),
                entity.getActualDate(), entity.getExpirationDate(), entity.getRemarks());
    }
}
