package com.example.demo.model;

import com.example.demo.model.audit.UserDateAudit;
import com.example.demo.model.audit.VenueType;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "venues")
public class Venue extends UserDateAudit {

    @Id
    @Column(name = "venue_id")
    private String id;

    @Column(name = "venue_name")
    private String name;

    @Column(name = "venue_address")
    private String address;

    @Column(name = "venue_email")
    private String email;

    @Column(name = "venue_phone_number")
    private String phoneNumber;

    @Column(name = "deactivated")
    private Boolean deactivated;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "venue_type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private VenueType venueType;
}
