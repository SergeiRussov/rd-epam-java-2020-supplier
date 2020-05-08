package com.epam.rd.service.stub;

import java.util.UUID;

public class StubForPaymentService {
    public UUID getAcceptanceId() {
        return UUID.randomUUID();
    }

    public String getCallbackUrl() {
        return "http://stub.com/Dont-forget-replace-all-stubs";
    }

    public String getStatus(UUID acceptanceId) {
        return "PROCESSING";
    }

    public void sendInvoiceId(UUID invoiceId) {

    }
}
