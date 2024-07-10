package com.E_commerce.E_commerce.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private int quantity;

    @Getter @Setter
    private double unitPrice;

    @Getter @Setter
    private double subTotal;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Getter @Setter
    private Products product;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @Getter @Setter
    private Invoice invoice;
}
