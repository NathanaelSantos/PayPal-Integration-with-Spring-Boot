package com.nathan.payment.services;

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

    public Payment creatPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl
    ) throws PayPalRESTException {

        Amount amount = getAmount(total, currency);
        Transaction transaction = createTransaction(description, amount);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        Payer payer = createPayer(method);
        RedirectUrls redirectUrls = createRedirectUrls(cancelUrl, successUrl);
        Payment payment = createPayment(payer, intent, transactions, redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePaymente(String paymentId,String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecution);
    }

    private Amount getAmount(Double total, String currency) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format(Locale.forLanguageTag(currency), "%.2f",total));

        return amount;
    }

    private Transaction createTransaction(String description, Amount amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);

        return transaction;
    }

    private Payer createPayer(String method) throws PayPalRESTException {
        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        return payer;
    }

    private Payment createPayment(Payer payer, String intent, List<Transaction> transactions, RedirectUrls redirectUrls) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setPayer(payer);
        payment.setIntent(intent);
        payment.setTransactions(transactions);
        payment.setRedirectUrls(redirectUrls);

        return payment;
    }

    private RedirectUrls createRedirectUrls(String cancelUrl, String successUrl) {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);

        return redirectUrls;
    }
}