package com.epam.rd.service;

import com.epam.rd.domain.Product;

import java.util.Scanner;

public class CreateProduct {
    public void createProduct() {
        ProductStorage productStorage = new ProductStorage();
        Scanner input = new Scanner(System.in);
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
        productStorage.insert(product);
    }
}
