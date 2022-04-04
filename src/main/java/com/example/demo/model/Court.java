package com.example.demo.model;


import com.example.demo.model.audit.UserDateAudit;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "court")
public class Court extends UserDateAudit {
    @Id
    @Column(name = "court_id")
    private String id;

    @Column(name = "court_name")
    private String name;

    @Column(name = "court_price")
    private Integer price;

    @Column(name = "court_number")
    private Byte number;

    @Column(name = "is_booked")
    private Boolean isBooked;

    @Column(name = "deactivated")
    private Boolean deactivated;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "court_type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CourtType courtType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "venue_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Venue venue;

}
