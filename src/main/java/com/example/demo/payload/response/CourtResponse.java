package com.example.demo.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class CourtResponse implements Serializable {
    private String id;
    private String name;
    private Integer price;
    private Byte number;
    private String courtType;
}
