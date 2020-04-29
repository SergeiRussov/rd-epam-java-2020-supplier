package com.epam.rd.service;

public class Main {
    public static void main(String[] args) {
       /* EntityManagerFactory emf = Persistence.createEntityManagerFactory("supplier-pu");
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(new Product("2"));
        manager.getTransaction().commit();
        manager.close();*/
        ProductsService productsService = new ProductsService();
        productsService.service();
    }
}
