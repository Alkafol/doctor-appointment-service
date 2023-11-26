package com.svi.group5.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "doctor")
public class Doctor extends User {

    @OneToMany(mappedBy="doctor")
    private Set<Appointment> appointments;
}
