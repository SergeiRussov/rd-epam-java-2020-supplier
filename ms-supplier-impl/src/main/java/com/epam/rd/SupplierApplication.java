package com.epam.rd;

import com.epam.rd.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SupplierApplication {
    public static void main(String[] args) {
        PaymentRepository p = new PaymentRepository();
        log.info("Hello world!");
    }
}
