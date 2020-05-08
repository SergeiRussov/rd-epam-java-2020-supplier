package com.epam.rd.service.impl;

import com.epam.rd.domain.Order;
import com.epam.rd.domain.Payment;
import com.epam.rd.repository.OrderRepository;
import com.epam.rd.repository.PaymentRepository;
import com.epam.rd.service.stub.StubForPaymentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private OrderRepository orderRepository = new OrderRepository();

    @Mock
    private PaymentRepository paymentRepository = new PaymentRepository();

    @Mock
    private StubForPaymentService stub = new StubForPaymentService();

    @InjectMocks
    PaymentServiceImpl paymentService = new PaymentServiceImpl();


    @Test
    public void createTest() {

        //Create Order based on which Payment will created
        Order testOrder = new Order();
        testOrder.setId(UUID.fromString("cb9bd7c5-8c0e-4059-9fdd-16929fc34708"));
        testOrder.setCustomer("STUB CUSTOMER");
        testOrder.setAmount(111);
        testOrder.setCreationDate(ZonedDateTime.now());
        orderRepository.save(testOrder);

        UUID uuid = paymentService.create(UUID.fromString("cb9bd7c5-8c0e-4059-9fdd-16929fc34708"));
        Optional<Payment> paymentOptional = paymentRepository.findById(uuid);

        assertTrue(paymentOptional.isPresent());
        Payment payment = paymentOptional.get();

        assertEquals(payment.getOrder().getId().toString(), "cb9bd7c5-8c0e-4059-9fdd-16929fc34708");
        assertEquals(payment.getOgrn().length(), 13);
        assertEquals(payment.getKpp().length(), 9);
        assertEquals(payment.getInn().length(), 12);
        assertEquals(payment.getRS().length(), 20);
        assertTrue(payment.getAmount() >= 0);
        assertNotNull(payment.getAcceptanceId());
        assertNotNull(payment.getPaymentCallbackUrl());
        assertTrue(payment.getStatus().equals("AWAITING") ||
                payment.getStatus().equals("PROCESSING") ||
                payment.getStatus().equals("PAID"));
        assertNotNull(payment.getInvoiceId());
        assertNotNull(payment.getStoreCallbackUrl());
        assertNotNull(payment.getCreationDate());
        assertNull(payment.getUpdateDate());
    }

    @Test
    public void updateTest() {
        UUID paymentUuid = paymentService.create(UUID.fromString("b6c2f230-ef6d-4e81-a893-220294866836"));
        paymentService.updateStatus(paymentUuid);

        Optional<Payment> paymentOptional = paymentRepository.findById(paymentUuid);
        assertTrue(paymentOptional.isPresent());
        Payment payment = paymentOptional.get();
        assertNotNull(payment.getUpdateDate());
        assertTrue(payment.getUpdateDate().compareTo(payment.getCreationDate()) > 0);
    }

}