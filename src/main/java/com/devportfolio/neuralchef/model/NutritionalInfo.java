package com.devportfolio.neuralchef.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nutritional_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NutritionalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double calories;

    @Column(nullable = false)
    private Double protein; // in Gramm

    @Column(nullable = false)
    private Double carbohydrates; // in Gramm

    @Column(nullable = false)
    private Double fat; // in Gramm

    @Column(nullable = false)
    private Double fiber; // in Gramm

    @Column(nullable = false)
    private Double sugar; // in Gramm

    @Column(nullable = false)
    private Double sodium; // in Milligramm
}
