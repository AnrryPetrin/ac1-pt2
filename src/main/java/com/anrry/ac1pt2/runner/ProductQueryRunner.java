package com.anrry.ac1pt2.runner;

import com.anrry.ac1pt2.model.Product;
import com.anrry.ac1pt2.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductQueryRunner implements CommandLineRunner {
    private final ProductRepository productRepository;

    public ProductQueryRunner(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        displayProducts("Products with price greater than 1000:",
                productRepository.findByPriceGreaterThan(1000.0));

        displayProducts("Products with price less than or equal to 1000:",
                productRepository.findByPriceLessThan(1000.0));

        displayProducts("Products with name containing 'a':",
                productRepository.findByNameContaining("a"));
    }

    private void displayProducts(String header, List<Product> products) {
        System.out.println("\n" + header);
        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        // Print table header
        System.out.printf("%-5s %-20s %-10s %-15s%n", "ID", "Name", "Price", "Category");
        System.out.println("-------------------------------------------------------------");

        // Print each product
        for (Product product : products) {
            System.out.printf(
                    "%-5d %-20s $%-9.2f %-15s%n",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getCategory() != null ? product.getCategory().getName() : "No Category"
            );
        }
    }
}
