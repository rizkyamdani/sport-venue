package com.example.demo.service;

import com.example.demo.model.Venue;
import com.example.demo.payload.request.VenueRequest;
import com.example.demo.payload.request.VenueUpdateRequest;
import com.example.demo.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
