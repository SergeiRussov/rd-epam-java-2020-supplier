package com.epam.rd.service.stub;

import java.util.UUID;

public class StubForPaymentService {

    public UUID getAcceptanceId() {
        return UUID.fromString("bd54af07-e873-4d29-a113-b7b662736ccf");
    }

    public String getCallbackUrl() {
        return "http://stub.com/Dont-forget-replace-all-stubs";
    }

    public StubData getData(){
        return new StubData();
    }
}
