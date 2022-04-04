package com.example.demo.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class CourtRequest implements Serializable {
    private String name;
    private Integer price;
    private Byte number;
    private Long courtTypeId;
    private String venueId;
}
