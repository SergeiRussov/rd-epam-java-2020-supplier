package com.epam.rd.service.stub;

import java.util.UUID;

public class StubForPaymentService {
    public UUID getAcceptanceId() {
        return UUID.fromString("bd54af07-e873-4d29-a113-b7b662736ccf");
    }

    public String getCallbackUrl() {
        return "http://stub.com/Dont-forget-replace-all-stubs";
    }

    public String getStatus(UUID acceptanceId) {
        return "PROCESSING";
    }

    public void sendInvoiceId(UUID invoiceId) {
    }

    public String getOrgn() {
        return "1234567890123";
    }

    public String getKpp() {
        return "123456789";
    }

    public String getInn() {
        return "123456789012";
    }

    public String getRS() {
        return "12345678901234567890";
    }
}
