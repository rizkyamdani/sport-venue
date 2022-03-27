package com.example.demo.service;

import com.example.demo.model.Court;
import com.example.demo.payload.request.CourtRequest;
import com.example.demo.payload.request.CourtUpdateRequest;
import com.example.demo.repository.CourtRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourtService {
    private final CourtRepository repository;

    public CourtService(CourtRepository repository) {
        this.repository = repository;

    }

    public List<Court>  getAllDataCourt() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        Iterable<Court> datas = repository.findAll();

        System.out.println(mapper.writeValueAsString(datas));

        List<Court> listCourt = new ArrayList<>();
//        datas.forEach(listCourt::add);
        return listCourt;
    }

    public void addDataCourt(CourtRequest request) {
        Court court =new Court();
        court.setName(request.getName());
        court.setAddress(request.getAddress());
        court.setNumber(request.getNumber());
        repository.save(court);
    }

    public void updateDataCourt(CourtUpdateRequest request){
        Court court = new Court();
        court.setId(request.getId());
        court.setName(request.getName());
        court.setAddress(request.getAddress());
        court.setNumber(request.getNumber());
        repository.save(court);
    }


}
