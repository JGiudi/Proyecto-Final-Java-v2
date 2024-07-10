package com.E_commerce.E_commerce.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private double price;
    @Getter @Setter
    private int stock;
    @Getter @Setter
    private boolean canBuy;

}
