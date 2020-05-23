package com.epam.rd.resource;

import com.epam.rd.dto.PaymentDto;
import com.epam.rd.service.PaymentValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.NoSuchAlgorithmException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class ControllerPayment {

    private final PaymentValidationService paymentValidationService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PaymentDto> registerPayd(@RequestBody PaymentDto paymentDto) throws NoSuchAlgorithmException {
       return ResponseEntity.ok(paymentValidationService.checkPaymentResult(paymentDto));
    }
}
