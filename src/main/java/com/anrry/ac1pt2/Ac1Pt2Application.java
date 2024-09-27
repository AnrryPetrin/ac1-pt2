package com.anrry.ac1pt2;

import com.anrry.ac1pt2.model.Category;
import com.anrry.ac1pt2.model.Product;
import com.anrry.ac1pt2.repository.CategoryRepository;
import com.anrry.ac1pt2.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Ac1Pt2Application implements CommandLineRunner {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public Ac1Pt2Application(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Ac1Pt2Application.class, args);
    }

    @Override
    public void run(String... args) {
        // Create categories
        Category electronics = new Category(1L, "Electronics");
        Category books = new Category(2L, "Books");

        // Save categories
        categoryRepository.saveAll(Arrays.asList(electronics, books));

        // Create products
        Product laptop = new Product(1L, "Laptop", 1200.0, electronics);
        Product smartphone = new Product(2L, "Smartphone", 800.0, electronics);
        Product novel = new Product(3L, "Novel Book", 20.0, books);

        // Save products
        productRepository.saveAll(Arrays.asList(laptop, smartphone, novel));

        // Print some output to verify
        System.out.println("Products and Categories are saved:");
        productRepository.findAll().forEach(System.out::println);
    }
}
