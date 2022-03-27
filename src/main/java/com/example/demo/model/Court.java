package com.example.demo.model;


import com.example.demo.model.audit.UserDateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "court")
public class Court extends UserDateAudit {
    @Id
    @Column(name = "court_id")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "court_name")
    private String name;

    @Column(name = "court_address")
    private String address;

    @Column(name = "court_number")
    private Byte number;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "court_type_id", nullable = false)
    private CourtType courtType;

}
