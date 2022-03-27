package com.example.demo.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;

@Setter
@Getter
public class CourtRequest implements Serializable {
    private String name;
    private String address;
    private Byte number;


}
