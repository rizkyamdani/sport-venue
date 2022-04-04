package com.example.demo.controller;

import com.example.demo.payload.request.CourtRequest;
import com.example.demo.payload.request.CourtUpdateRequest;
import com.example.demo.payload.response.CourtResponse;
import com.example.demo.service.CourtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courts")
public class CourtController {
    private final CourtService service;

    public CourtController(CourtService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity getAllData() throws JsonProcessingException {
        List<CourtResponse> data = service.getAllDataCourt();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping

    public ResponseEntity addData(@RequestBody CourtRequest request) throws JsonProcessingException {
        service.addDataCourt(request);
        return new ResponseEntity("SUCCESS" , HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity modifyData (@RequestBody CourtUpdateRequest request) {
        service.updateDataCourt(request);
        return new ResponseEntity("SUCCESS" , HttpStatus.CREATED);
    }
}
