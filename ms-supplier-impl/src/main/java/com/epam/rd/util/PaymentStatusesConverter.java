package com.epam.rd.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter

public class PaymentStatusesConverter implements AttributeConverter<PaymentStatus, String> {

    @Override
    public String convertToDatabaseColumn(PaymentStatus status) {
        if (status == null) {
            return null;
        }
        return status.toString();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return PaymentStatus.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
