package com.example.demo.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class VenueUpdateRequest implements Serializable {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
}
