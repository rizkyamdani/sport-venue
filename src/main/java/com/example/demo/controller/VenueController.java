package com.example.demo.controller;

import com.example.demo.model.Venue;
import com.example.demo.payload.request.VenueRequest;
import com.example.demo.payload.request.VenueUpdateRequest;
import com.example.demo.payload.response.VenueResponse;
import com.example.demo.service.VenueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
public class VenueController {

    private final VenueService service;


    public VenueController(VenueService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getAllData() throws JsonProcessingException {
        List<VenueResponse> data = service.getAllDataVenue();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addData(@RequestBody VenueRequest request) throws JsonProcessingException {
        service.addDataVenue(request);
        return new ResponseEntity("SUCCESS", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity modifiedData(@RequestBody VenueUpdateRequest request) {
        service.updateData(request);
        return new ResponseEntity("SUCCESS", HttpStatus.CREATED);
    }
}
