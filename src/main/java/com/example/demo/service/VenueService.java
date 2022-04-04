package com.example.demo.service;

import com.example.demo.model.Venue;
import com.example.demo.payload.request.MaintenanceRequest;
import com.example.demo.payload.request.VenueRequest;
import com.example.demo.payload.request.VenueUpdateRequest;
import com.example.demo.payload.response.CourtResponse;
import com.example.demo.payload.response.VenueResponse;
import com.example.demo.repository.VenueRepository;
import com.example.demo.util.CommonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VenueService {

    private final VenueRepository repository;
    private final MaintenanceService maintenanceService;
    private final VenueTypeService venueTypeService;

    public VenueService(VenueRepository repository, MaintenanceService maintenanceService, VenueTypeService venueTypeService) {
        this.repository = repository;
        this.maintenanceService = maintenanceService;
        this.venueTypeService = venueTypeService;
    }

    public List<VenueResponse> getAllDataVenue() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        Iterable<Venue> datas = repository.findAll();

        List<VenueResponse>listVenue =new ArrayList<>();
        datas.forEach(venue -> {
            VenueResponse response = new VenueResponse();
            response.setId(venue.getId());
            response.setName(venue.getName());
            response.setAddress(venue.getAddress());
            response.setEmail(venue.getEmail());
            response.setVenueType(venue.getVenueType().getName());
            listVenue.add(response);
        });

        return listVenue;
    }

    public void addDataVenue(VenueRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String id = CommonUtil.generateId();
        Venue venue = new Venue();
        venue.setId(id);
        venue.setName(request.getName());
        venue.setAddress(request.getAddress());
        venue.setEmail(request.getEmail());
        venue.setVenueType(venueTypeService.getDataById(request.getVenueTypeId()));
        venue.setPhoneNumber(request.getPhoneNumber());

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setPrimaryNumber(id);
        maintenanceRequest.setTransactionType("VENUE");
        maintenanceRequest.setOldValue(mapper.writeValueAsString(venue));
        maintenanceService.addDataMaintenance(maintenanceRequest);
    }

    public void updateData(VenueUpdateRequest request) {
        Venue venue = new Venue();
        venue.setId(request.getId());
        venue.setName(request.getName());
        venue.setAddress(request.getAddress());
        venue.setEmail(request.getEmail());
        venue.setPhoneNumber(request.getPhoneNumber());
        repository.save(venue);
    }

    public Venue getDataById(String id) {
        Optional<Venue> dataVenue = repository.findById(id);
        Venue venue = new Venue();

       dataVenue.ifPresentOrElse(venue1 -> {
           venue.setId(venue1.getId());
           venue.setName(venue1.getName());
           venue.setAddress(venue1.getAddress());
           venue.setEmail(venue1.getEmail());
           venue.setPhoneNumber(venue1.getPhoneNumber());
       }, () -> {});

       return venue;
    }
}
