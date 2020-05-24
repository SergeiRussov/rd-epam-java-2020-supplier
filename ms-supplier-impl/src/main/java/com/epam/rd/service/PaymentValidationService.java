package com.epam.rd.service;

import com.epam.rd.dto.PaymentDto;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PaymentValidationService {

    public PaymentDto checkPaymentResult(PaymentDto paymentDto) throws NoSuchAlgorithmException {
        String data = paymentDto.getOgrn() + ":" + paymentDto.getKpp() + ":" + paymentDto.getInn() + ":" + paymentDto.getAccountNumber();
        paymentDto.setResult(false);
        if (isValidData(data)) {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
            if (myHash.equals(paymentDto.getMd5())) {
                paymentDto.setResult(true);
            }
        }
        return paymentDto;
    }

    private boolean isValidData(String data) {
        Pattern pattern = Pattern.compile("^\\d{13}:\\d{9}:\\d{10}(\\d{2})?:\\d{12}$");
        Matcher matcher = pattern.matcher(data);
        return matcher.find();
    }
}
