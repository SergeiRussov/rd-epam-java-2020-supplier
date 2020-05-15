package com.epam.rd.config;

import com.epam.rd.service.ItemService;
import com.epam.rd.service.OrderService;
import com.epam.rd.service.PaymentService;
import com.epam.rd.service.ProductService;
import com.epam.rd.service.impl.ItemServiceImpl;
import com.epam.rd.service.impl.OrderServiceImpl;
import com.epam.rd.service.impl.PaymentServiceImpl;
import com.epam.rd.service.impl.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public ItemService beanItemService() {
        return new ItemServiceImpl();
    }

    @Bean
    public OrderService beanOrderService() {
        return new OrderServiceImpl();
    }

    @Bean
    public PaymentService beanPaymentService() {
        return new PaymentServiceImpl();
    }

    @Bean
    public ProductService beanProductService() {
        return new ProductServiceImpl();
    }
}
