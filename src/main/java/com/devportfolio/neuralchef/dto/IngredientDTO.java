package com.devportfolio.neuralchef.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {
    private Long id;

    @NotBlank(message = "Name darf nicht leer sein")
    private String name;

    @NotNull(message = "Menge ist erforderlich")
    @Positive(message = "Menge muss positiv sein")
    private Double quantity;

    @NotBlank(message = "Einheit darf nicht leer sein")
    private String unit;
}
