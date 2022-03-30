package com.example.demo.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CourtTypeRequest implements Serializable {
    private String name;
}
