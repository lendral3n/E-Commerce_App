package com.l3n.ecommerceapp.ecommmerce_app.service;

import com.l3n.ecommerceapp.ecommmerce_app.model.*;
import com.xendit.exception.XenditException;
import com.xendit.Xendit;
import com.xendit.model.FixedVirtualAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class XenditService {

    // @Value("${xendit.api.key}")
    // private String xenditApiKey;

    // public PaymentResponse createVirtualAccount(PaymentRequest paymentRequest) throws XenditException {
    // Xendit.apiKey = xenditApiKey;

    // Map<String, Object> params = new HashMap<>();
    // params.put("external_id", UUID.randomUUID().toString());
    // params.put("bank_code", paymentRequest.getMethod());
    // params.put("expected_amount", paymentRequest.getTotalPrice());
    // params.put("name", paymentRequest.getCustomer().getName()); // Asumsi bahwa CustomerRequest hanya memiliki field 'name'

    // FixedVirtualAccount charge = FixedVirtualAccount.createClosed(params);

    // return new PaymentResponse(
    //         charge.getId(),
    //         charge.getStatus(),
    //         charge.getBankCode(),
    //         charge.getCurrency(),
    //         charge.getExpectedAmount(),
    //         LocalDateTime.now().toString(),
    //         charge.getAccountNumber()
    //     );
    // }
}
