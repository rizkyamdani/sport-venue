package com.example.demo.service;

import com.example.demo.model.Court;
import com.example.demo.payload.request.CourtRequest;
import com.example.demo.payload.request.CourtUpdateRequest;
import com.example.demo.payload.response.CourtResponse;
import com.example.demo.repository.CourtRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourtService {
    private final CourtRepository repository;
    private final CourtTypeService courtTypeService;
    private final VenueService venueService;

    public CourtService(CourtRepository repository, CourtTypeService courtTypeService, VenueService venueService) {
        this.repository = repository;

        this.courtTypeService = courtTypeService;
        this.venueService = venueService;
    }

    public List<CourtResponse>  getAllDataCourt() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        Iterable<Court> datas = repository.findAll();

        List<CourtResponse> listCourt = new ArrayList<>();
        datas.forEach(court -> {
            CourtResponse response = new CourtResponse();
            response.setId(court.getId());
            response.setName(court.getName());
            response.setPrice(court.getPrice());
            response.setNumber(court.getNumber());
            response.setCourtType(court.getCourtType().getName());
            listCourt.add(response);
        });


        return listCourt;
    }

    public void addDataCourt(CourtRequest request) {
        Court court =new Court();
        court.setName(request.getName());
        court.setPrice(request.getPrice());
        court.setNumber(request.getNumber());
        court.setCourtType(courtTypeService.getDataById(request.getCourtTypeId()));
        court.setVenue(venueService.getDataById(request.getVenueId()));
        repository.save(court);

    }

    public void updateDataCourt(CourtUpdateRequest request){
        Court court = new Court();
        court.setId(request.getId());
        court.setName(request.getName());
        court.setPrice(request.getPrice());
        court.setNumber(request.getNumber());
        repository.save(court);
    }


}
