package com.epam.rd.service;

import com.epam.rd.domain.Product;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class CreateProduct {
    private static final String EXCEPTION = "Формат ввода не верен ";

    public void createProduct() {
        ProductRepository productRepository = new ProductRepository();
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Введите name:");
            String name = input.nextLine();
            System.out.println("Введите description:");
            String description = input.nextLine();
            System.out.println("Введите price:");
            int price = Integer.parseInt(input.nextLine());
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            productRepository.insert(product);
        } catch (CommandExecutionException e) {
            log.debug(EXCEPTION, e);
        }
    }
}
