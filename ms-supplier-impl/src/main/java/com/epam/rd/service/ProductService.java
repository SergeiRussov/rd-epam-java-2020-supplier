package com.epam.rd.service;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.UUID;

@Slf4j
public class ProductService {
    private static final String EXCEPTION = "Произошло исключение при обработке команды ";

    public String service() {
        ProductRepository productRepository = new ProductRepository();
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Введите команду ");
            String command = input.nextLine();

            if (command.equals("find")) {
                try {
                    System.out.println("Введите id для поиска:");
                    String id = input.nextLine();
                    log.info(String.valueOf(productRepository.findById((UUID.fromString(id)))));
                } catch (CommandProcessingException e) {
                    log.debug(EXCEPTION, e);
                }

            } else if (command.equals("delete")) {
                try {
                    System.out.println("Введите id строки для удаления");
                    String id = input.nextLine();
                    productRepository.delete(UUID.fromString(id));
                } catch (CommandProcessingException e) {
                    log.debug(EXCEPTION, e);
                }

            } else if (command.equals("update")) {
                try {
                    System.out.println("Введите id строки для редактирования");
                    String id = input.nextLine();
                    System.out.println("Введите новое значение name:");
                    String name = input.nextLine();
                    productRepository.updateName(UUID.fromString(id), name);
                    log.info(String.valueOf(productRepository.findById((UUID.fromString(id)))));
                } catch (CommandProcessingException e) {
                    log.debug(EXCEPTION, e);
                }

            } else if (command.equals("insert")) {
                CreateProduct createProduct = new CreateProduct();
                createProduct.createProduct();
            } else {
                log.info("Команда не существует");
                throw new CommandSelectionException("Команда не существует");
            }

        }
    }
}

//  try {
//                operation = new Add();
//                operation.handle(argument.getCommand(), argument.getLine(), argument.getFileName(), argument.getText());
//            } catch (CommandExecutionException e) {
//                logger.debug(EXCEPTION, e);
//            }