package com.example.demo.model;

import com.example.demo.model.audit.UserDateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "venues")
public class Venue extends UserDateAudit {

    @Id
    @Column(name = "venue_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "venue_name")
    private String name;

    @Column(name = "venue_address")
    private String address;

    @Column(name = "venue_email")
    private String email;

    @Column(name = "venue_phone_number")
    private String phoneNumber;

}
