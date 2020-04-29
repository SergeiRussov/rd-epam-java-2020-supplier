package com.epam.rd.service;

import java.util.Scanner;
import java.util.UUID;

public class ProductsService {

    public String service() {
        ProductStorage productStorage = new ProductStorage();
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Введите команду ");
            String command = input.nextLine();
            if (command.equals("find")) {
                System.out.println("Введите id для поиска:");
                String id = input.nextLine();
                System.out.println(productStorage.findById(UUID.fromString(id)));

            } else if (command.equals("delete")) {
                System.out.println("Введите id строки для удаления");
                String id = input.nextLine();
                productStorage.delete(UUID.fromString(id));

            } else if (command.equals("update")) {
                System.out.println("Введите id строки для редактирования");
                String id = input.nextLine();
                System.out.println("Введите новое значение name:");
                String name = input.nextLine();
                productStorage.updateName(UUID.fromString(id), name);
                System.out.println(productStorage.findById(UUID.fromString(id)));

            } else if (command.equals("insert")) {
                CreateProduct createProduct = new CreateProduct();
                createProduct.createProduct();
            }
        }
    }
}
