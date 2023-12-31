package com.svi.group5.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "doctor")
public class Doctor extends User {
    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
}
