package com.example.demo.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class CourtUpdateRequest implements Serializable {
    private Long id;
    private String name;
    private Integer price;
    private Byte number;

}
