package com.example.demo.service;

import com.example.demo.model.Venue;
import com.example.demo.payload.request.VenueRequest;
import com.example.demo.payload.request.VenueUpdateRequest;
import com.example.demo.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VenueService {

    private final VenueRepository repository;

    public VenueService(VenueRepository repository) {
        this.repository = repository;
    }

    public List<Venue> getAllDataVenue() {
        Iterable<Venue> datas = repository.findAll();
        List<Venue> listVenue = new ArrayList<>();
        datas.forEach(listVenue::add);

        return listVenue;
    }

    public void addDataVenue(VenueRequest request) {
        Venue venue = new Venue();
        venue.setName(request.getName());
        venue.setAddress(request.getAddress());
        venue.setEmail(request.getEmail());
        venue.setPhoneNumber(request.getPhoneNumber());
        repository.save(venue);
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

    public Venue getDataById(Long id) {
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
