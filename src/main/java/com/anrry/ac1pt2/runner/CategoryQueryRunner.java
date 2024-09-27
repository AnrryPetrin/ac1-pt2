package com.anrry.ac1pt2.runner;

import com.anrry.ac1pt2.model.Category;
import com.anrry.ac1pt2.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryQueryRunner implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    public CategoryQueryRunner(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        displayCategoriesWithNameContaining();
        displayCategoryWithIdAndProducts();
    }

    private void displayCategoriesWithNameContaining() {
        List<Category> categories = categoryRepository.findByNameContaining("a");

        System.out.println("\nCategories with name containing '" + "a" + "':");
        if (categories.isEmpty()) {
            System.out.println("No categories found.");
            return;
        }

        // Print table header
        System.out.printf("%-5s %-20s%n", "ID", "Name");
        System.out.println("------------------------------");

        // Print each category
        for (Category category : categories) {
            System.out.printf("%-5d %-20s%n", category.getId(), category.getName());
        }
    }

    private void displayCategoryWithIdAndProducts() {
        Category category = categoryRepository.findByIdAndFetchProducts(1L);

        System.out.println("\nCategory with ID " + 1L + " and its products:");
        if (category == null) {
            System.out.println("Category not found.");
            return;
        }

        // Print category details
        System.out.println("Category ID: " + category.getId());
        System.out.println("Category Name: " + category.getName());

        // Print products in a table
        List<com.anrry.ac1pt2.model.Product> products = category.getProducts();
        if (products.isEmpty()) {
            System.out.println("No products in this category.");
            return;
        }

        System.out.printf("\n%-5s %-20s %-10s%n", "ID", "Name", "Price");
        System.out.println("-----------------------------------------");

        for (com.anrry.ac1pt2.model.Product product : products) {
            System.out.printf("%-5d %-20s $%-9.2f%n",
                    product.getId(),
                    product.getName(),
                    product.getPrice());
        }
    }
}
