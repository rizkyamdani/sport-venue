package com.example.demo.service;

import com.example.demo.model.Court;
import com.example.demo.model.Venue;
import com.example.demo.payload.request.CourtRequest;
import com.example.demo.payload.request.CourtUpdateRequest;
import com.example.demo.payload.request.MaintenanceRequest;
import com.example.demo.payload.response.CourtResponse;
import com.example.demo.repository.CourtRepository;
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
public class CourtService {
    private final CourtRepository repository;
    private final CourtTypeService courtTypeService;
    private final VenueService venueService;
    private final MaintenanceService maintenanceService;

    public CourtService(CourtRepository repository, CourtTypeService courtTypeService, VenueService venueService, MaintenanceService maintenanceService) {
        this.repository = repository;
        this.courtTypeService = courtTypeService;
        this.venueService = venueService;
        this.maintenanceService = maintenanceService;
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

    public void addDataCourt(CourtRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String id = CommonUtil.generateId();
        Court court =new Court();
        court.setId(id);
        court.setName(request.getName());
        court.setPrice(request.getPrice());
        court.setNumber(request.getNumber());
        court.setCourtType(courtTypeService.getDataById(request.getCourtTypeId()));
        court.setVenue(venueService.getDataById(request.getVenueId()));


        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setPrimaryNumber(id);
        maintenanceRequest.setTransactionType("COURT");
        maintenanceRequest.setOldValue(mapper.writeValueAsString(court));
        maintenanceService.addDataMaintenance(maintenanceRequest);

    }

    public void updateDataCourt(CourtUpdateRequest request){
        Court court = new Court();
        court.setId(request.getId());
        court.setName(request.getName());
        court.setPrice(request.getPrice());
        court.setNumber(request.getNumber());
        repository.save(court);
    }
     public Court getDataById(String id) {
         Optional<Court> dataCourt = repository.findById(id);
         Court court = new Court();

         dataCourt.ifPresentOrElse(court1 -> {
             court.setId(court1.getId());
             court.setName(court1.getName());
             court.setPrice(court1.getPrice());
             court.setNumber(court1.getNumber());
         }, () -> {});
         return court;
     }



}
