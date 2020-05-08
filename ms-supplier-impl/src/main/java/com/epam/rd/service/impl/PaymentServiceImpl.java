package com.epam.rd.service.impl;

import com.epam.rd.domain.Order;
import com.epam.rd.domain.Payment;
import com.epam.rd.repository.OrderRepository;
import com.epam.rd.repository.PaymentRepository;
import com.epam.rd.service.PaymentService;
import com.epam.rd.service.stub.StubForPaymentService;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private OrderRepository orderRepository = new OrderRepository();
    private PaymentRepository paymentRepository = new PaymentRepository();
    private StubForPaymentService stub = new StubForPaymentService();
    private static final HashMap<String, String> paymentStatesConverter = new HashMap<>();
    static {
        paymentStatesConverter.put("PROCESSING", "PROCESSING");
        paymentStatesConverter.put("COMPLETED", "PAID");
    }

    @Override
    public UUID create(UUID orderUUID) {
        Optional<Order> orderOptional = orderRepository.findById(orderUUID);
        if (orderOptional.isPresent()){
            Payment payment = new Payment();
            Order order = orderOptional.get();
            payment.setId(UUID.randomUUID());
            payment.setOrder(order);

            //TODO Replace stub data
            payment.setOgrn("1234567890123");
            payment.setKpp("123456789");
            payment.setInn("123456789012");
            payment.setRS("12345678901234567890");
            payment.setAcceptanceId(stub.getAcceptanceId());
            payment.setPaymentCallbackUrl(stub.getCallbackUrl());
            payment.setStoreCallbackUrl(stub.getCallbackUrl());

            payment.setStatus("AWAITING");
            payment.setAmount(order.getAmount());
            payment.setInvoiceId(UUID.randomUUID());
            // Стабовая отправка айдишника
            stub.sendInvoiceId(payment.getInvoiceId());

            payment.setCreationDate(OffsetDateTime.now());
            paymentRepository.save(payment);
            return payment.getId();
        } else {
            log.warn("Order with specified UUID is not presented in repository: {}", orderUUID);
            return null;
        }
    }

    @Override
    public void updateStatus(UUID paymentUUID) {
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentUUID);
        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            String newStatus = stub.getStatus(payment.getAcceptanceId());
            payment.setStatus(paymentStatesConverter.get(newStatus));
            log.info("Changed status for Payment with UUID {} to {}", payment.getId(), newStatus);
            payment.setUpdateDate(OffsetDateTime.now());
            paymentRepository.save(payment);
        } else {
            log.warn("Payment with specified UUID is not presented in repository: {}", paymentUUID);
        }
    }
}


