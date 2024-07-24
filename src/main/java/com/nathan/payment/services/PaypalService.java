package com.nathan.payment.services;

import com.nathan.payment.factory.PaymentFactory;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PaypalService {
    private final APIContext apiContext;
    private final PaymentFactory paymentFactory = new PaymentFactory();

    public Payment creatPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl
    ) throws PayPalRESTException {

        Amount amount = paymentFactory.createAmount(total, currency);
        Transaction transaction = paymentFactory.createTransaction(description, amount);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        Payer payer = paymentFactory.createPayer(method);
        RedirectUrls redirectUrls = paymentFactory.createRedirectUrls(cancelUrl, successUrl);
        Payment payment = paymentFactory.createPayment(payer, intent, transactions, redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePaymente(String paymentId,String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecution);
    }
}