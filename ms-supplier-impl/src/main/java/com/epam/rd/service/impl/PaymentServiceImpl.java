package com.epam.rd.service.impl;

import com.epam.rd.domain.Order;
import com.epam.rd.domain.Payment;
import com.epam.rd.repository.OrderRepository;
import com.epam.rd.repository.PaymentRepository;
import com.epam.rd.service.PaymentService;
import com.epam.rd.service.stub.StubForPaymentService;
import com.epam.rd.util.PaymentStates;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;
    private StubForPaymentService stub;
    private static HashMap<String, PaymentStates> paymentStatesConverter;

    public PaymentServiceImpl() {
        orderRepository = new OrderRepository();
        paymentRepository = new PaymentRepository();
        stub = new StubForPaymentService();
        paymentStatesConverter = new HashMap<>();
        paymentStatesConverter.put("PROCESSING", PaymentStates.PROCESSING);
        paymentStatesConverter.put("COMPLETED", PaymentStates.PAID);
    }

    @Override
    public UUID create(UUID orderUUID) {
        Optional<Order> orderOptional = orderRepository.findById(orderUUID);
        if (orderOptional.isEmpty()) {
            log.warn("Order with specified UUID is not presented in repository: {}", orderUUID);
            return null;
        }
        Payment payment = new Payment();
        Order order = orderOptional.get();
        payment.setId(UUID.randomUUID());
        payment.setOrder(order);

        //TODO Replace stub data
        payment.setOgrn(stub.getOrgn());
        payment.setKpp(stub.getKpp());
        payment.setInn(stub.getInn());
        payment.setRS(stub.getRS());
        payment.setAcceptanceId(stub.getAcceptanceId());
        payment.setPaymentCallbackUrl(stub.getCallbackUrl());
        payment.setStoreCallbackUrl(stub.getCallbackUrl());

        payment.setStatus(PaymentStates.AWAITING);
        payment.setAmount(order.getAmount());
        payment.setInvoiceId(UUID.randomUUID());
        // Стабовая отправка айдишника
        stub.sendInvoiceId(payment.getInvoiceId());

        payment.setCreationDate(OffsetDateTime.now());
        paymentRepository.save(payment);
        return payment.getAcceptanceId();
    }

    @Override
    public void updateStatus(UUID paymentUUID) {
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentUUID);
        if (paymentOptional.isEmpty()) {
            log.warn("Payment with specified UUID is not presented in repository: {}", paymentUUID);
            return;
        }
        Payment payment = paymentOptional.get();
        String newStatus = stub.getStatus(payment.getAcceptanceId());
        payment.setStatus(paymentStatesConverter.get(newStatus));
        log.info("Changed status for Payment with UUID {} to {}", payment.getId(), newStatus);
        payment.setUpdateDate(OffsetDateTime.now());
        paymentRepository.save(payment);
    }
}


