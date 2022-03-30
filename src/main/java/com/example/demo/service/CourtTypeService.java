package com.example.demo.service;

import com.example.demo.model.CourtType;
import com.example.demo.payload.request.CourtTypeRequest;
import com.example.demo.repository.CourtTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourtTypeService {

    private final CourtTypeRepository repository;

    public CourtTypeService(CourtTypeRepository repository) {
        this.repository = repository;
    }

    public CourtType getDataById(Long id) {
        Optional<CourtType> dataCourtType = repository.findById(id);
        CourtType courtType = new CourtType();

        dataCourtType.ifPresentOrElse(court -> {
            courtType.setId(court.getId());
            courtType.setName(court.getName());
        }, () -> {});

        return courtType;
    }

    public void addDataCourtType(CourtTypeRequest request){
        CourtType courtType = new CourtType();
        courtType.setName(request.getName());
        repository.save(courtType);
    }

}
