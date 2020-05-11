package com.epam.rd.service.impl;

import com.epam.rd.domain.Order;
import com.epam.rd.domain.Payment;
import com.epam.rd.repository.OrderRepository;
import com.epam.rd.repository.PaymentRepository;
import com.epam.rd.service.stub.StubForPaymentService;
import com.epam.rd.util.PaymentStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private OrderRepository orderRepository = new OrderRepository();

    @Mock
    private PaymentRepository paymentRepository = new PaymentRepository();

    @Mock
    private StubForPaymentService stubService = new StubForPaymentService();

    @InjectMocks
    PaymentServiceImpl paymentService = new PaymentServiceImpl();

    @Test
    public void createTest() {
        UUID acceptanceUUID = UUID.fromString("bd54af07-e873-4d29-a113-b7b662736ccf");
        UUID orderUUID = UUID.fromString("cb9bd7c5-8c0e-4059-9fdd-16929fc34708");
        Order stubOrder = new Order();
        stubOrder.setId(orderUUID);
        stubOrder.setAmount(111);

        when(orderRepository.findById(orderUUID)).thenReturn(Optional.of(stubOrder));
        when(stubService.getAcceptanceId()).thenReturn(acceptanceUUID);

        UUID uuid = paymentService.create(UUID.fromString("cb9bd7c5-8c0e-4059-9fdd-16929fc34708"));

        assertEquals(uuid, acceptanceUUID);
        verify(paymentRepository, times(1)).save(any());
    }

    @Test
    public void updateTest() {
        UUID paymentUUID = UUID.fromString("b6c2f230-ef6d-4e81-a893-220294866836");
        Payment stubPayment = new Payment();
        stubPayment.setPaymentStatus(PaymentStatus.AWAITING);
        stubPayment.setCreationDate(OffsetDateTime.now());
        Optional<Payment> stubPaymentOptional = Optional.of(stubPayment);

        when(paymentRepository.findById(paymentUUID)).thenReturn(stubPaymentOptional);

        paymentService.markAsPaid(paymentUUID);

        verify(paymentRepository, times(1)).save(any());
        assertEquals(stubPayment.getPaymentStatus(), PaymentStatus.PAID);
        assertTrue(stubPayment.getUpdateDate().compareTo(stubPayment.getCreationDate()) > 0);
    }
}