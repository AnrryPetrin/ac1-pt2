package com.anrry.ac1pt2.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ToString.Exclude // Prevents circular reference in toString()
    private Category category;

    // Optionally, override toString for custom formatting
    @Override
    public String toString() {
        return String.format("Product [ID=%d, Name='%s', Price=%.2f, Category='%s']",
                id, name, price, category != null ? category.getName() : "No Category");
    }

}