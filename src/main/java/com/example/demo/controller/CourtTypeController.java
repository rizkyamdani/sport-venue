package com.example.demo.controller;

import com.example.demo.model.CourtType;
import com.example.demo.payload.request.CourtTypeRequest;
import com.example.demo.service.CourtTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/court-type")
public class CourtTypeController {
private final CourtTypeService service;

    public CourtTypeController(CourtTypeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity addData(@RequestBody CourtTypeRequest request) {
        service.addDataCourtType(request);
        return new ResponseEntity("SUCCESS", HttpStatus.CREATED);
    }
}
