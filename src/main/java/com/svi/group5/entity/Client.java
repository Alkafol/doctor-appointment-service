package com.svi.group5.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client extends User {
    @OneToMany(mappedBy="client")
    private Set<Appointment> appointments;
}
