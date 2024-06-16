package com.l3n.ecommerceapp.ecommmerce_app.service;

import com.xendit.exception.XenditException;
import com.xendit.model.Invoice;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class XenditService {

    public Invoice createInvoice(String externalId, String payerEmail, BigDecimal amount) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("external_id", externalId);
            params.put("payer_email", payerEmail);
            params.put("description", "Order Invoice");
            params.put("amount", amount);

            return Invoice.create(params);
        } catch (XenditException e) {
            throw new RuntimeException("Error creating invoice with Xendit: " + e.getMessage(), e);
        }
    }
}
