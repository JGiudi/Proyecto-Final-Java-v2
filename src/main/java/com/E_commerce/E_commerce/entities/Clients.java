package com.E_commerce.E_commerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String surname;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String street;

    @Getter @Setter
    private int dni;

    @Getter @Setter
    private int points;

    @OneToMany(mappedBy = "clients", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<Invoice> invoices;
}
