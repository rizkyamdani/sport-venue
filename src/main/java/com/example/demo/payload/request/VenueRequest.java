package com.example.demo.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class VenueRequest implements Serializable {
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private Long venueTypeId;
}
