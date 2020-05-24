package com.epam.rd.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PaymentDto {
    private UUID id;
    private String inn;
    private String kpp;
    private String ogrn;
    private String accountNumber;
    private String md5;
    private Boolean result;

}
