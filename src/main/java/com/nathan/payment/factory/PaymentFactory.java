package com.nathan.payment.factory;

import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;

import java.util.List;
import java.util.Locale;

public class PaymentFactory{
    public Amount createAmount(Double total, String currency) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format(Locale.forLanguageTag(currency), "%.2f",total));

        return amount;
    }

    public Transaction createTransaction(String description, Amount amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);

        return transaction;
    }

    public Payer createPayer(String method) throws PayPalRESTException {
        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        return payer;
    }

    public Payment createPayment(Payer payer, String intent, List<Transaction> transactions, RedirectUrls redirectUrls) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setPayer(payer);
        payment.setIntent(intent);
        payment.setTransactions(transactions);
        payment.setRedirectUrls(redirectUrls);

        return payment;
    }

    public RedirectUrls createRedirectUrls(String cancelUrl, String successUrl) {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);

        return redirectUrls;
    }
}