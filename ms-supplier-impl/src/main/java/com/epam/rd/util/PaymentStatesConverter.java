package com.epam.rd.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter

public class PaymentStatesConverter implements AttributeConverter<PaymentStates, String> {

    @Override
    public String convertToDatabaseColumn(PaymentStates status) {
        if (status == null) {
            return null;
        }
        return status.toString();
    }

    @Override
    public PaymentStates convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return PaymentStates.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
