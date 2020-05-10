package com.epam.rd.service.impl;

import com.epam.rd.domain.Order;
import com.epam.rd.domain.Payment;
import com.epam.rd.repository.OrderRepository;
import com.epam.rd.repository.PaymentRepository;
import com.epam.rd.service.PaymentService;
import com.epam.rd.service.stub.StubData;
import com.epam.rd.service.stub.StubForPaymentService;
import com.epam.rd.util.PaymentStatus;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;
    private StubForPaymentService stubService;

    public PaymentServiceImpl() {
        orderRepository = new OrderRepository();
        paymentRepository = new PaymentRepository();
        stubService = new StubForPaymentService();
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
        StubData stubData = stubService.getData();
        payment.setOgrn(stubData.getOgrn());
        payment.setKpp(stubData.getKpp());
        payment.setInn(stubData.getInn());
        payment.setRS(stubData.getRs());
        payment.setAcceptanceId(stubService.getAcceptanceId());
        payment.setPaymentCallbackUrl(stubService.getCallbackUrl());
        payment.setStoreCallbackUrl(stubService.getCallbackUrl());

        payment.setPaymentStatus(PaymentStatus.AWAITING);
        payment.setAmount(order.getAmount());
        payment.setInvoiceId(UUID.randomUUID());

        payment.setCreationDate(OffsetDateTime.now());
        paymentRepository.save(payment);
        return payment.getAcceptanceId();
    }

    @Override
    public void markAsPaid(UUID paymentUUID) {
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentUUID);
        if (paymentOptional.isEmpty()) {
            log.warn("Payment with specified UUID is not presented in repository: {}", paymentUUID);
            return;
        }
        Payment payment = paymentOptional.get();
        payment.setPaymentStatus(PaymentStatus.PAID);
        payment.setUpdateDate(OffsetDateTime.now());
        log.info("Payment with UUID={} marked as PAID", payment.getId());
        paymentRepository.save(payment);
    }
}


