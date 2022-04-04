package com.example.demo.model;

import com.example.demo.model.audit.UserDateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "court_type")
public class CourtType extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "court_type_id")
    private Long id;

    @Column(name = "court_type_name")
    private String name;
}
