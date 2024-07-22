package com.E_commerce.E_commerce.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private LocalDateTime dateEmission;

    @Getter @Setter
    private String payMethod;

    @Getter @Setter
    private double total;

    @Getter @Setter
    private String state;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @Getter @Setter
    private Clients clients;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<DetailInvoice> detailInvoices;
}
