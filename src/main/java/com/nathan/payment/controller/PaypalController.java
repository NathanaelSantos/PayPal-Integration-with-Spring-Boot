package com.nathan.payment.controller;

import com.nathan.payment.services.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class PaypalController {
    private static final Logger log = Logger.getLogger(PaypalController.class.getName());
    private final PaypalService paypalService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @PostMapping("/payment/create")
    public RedirectView createPayment(
            @RequestParam("method") String method,
            @RequestParam("amount") String amount,
            @RequestParam("currency") String currency,
            @RequestParam("description") String description
    )
    {
        try {
            String cancelUrl = "http://localhost:8080/paypal/cancel";
            String successUrl = "http://localhost:8080/paypal/success";

            Payment payment = paypalService.creatPayment(
                    Double.valueOf(amount),
                    currency,
                    method,
                    "sale",
                    description,
                    cancelUrl,
                    successUrl
            );

            for (Links links: payment.getLinks()) {
                if(links.getRel().equals("approval_url")) {
                    return new RedirectView(links.getHref());
                }
            }

        }catch (PayPalRESTException e){
            log.warning("Error occurred " + e);
        }

        return new RedirectView("/payment/error");
    }

    @GetMapping("/paypal/success")
    public String successPayment(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId
            ){
        try {
            Payment payment = paypalService.executePaymente(paymentId, payerId);
            if(payment.getState().equals("approved")){
                return "paymentSuccess";
            }
        }catch (PayPalRESTException e){
            log.warning("Error occurred " + e);
        }

        return "paymentSuccess";
    }

    @GetMapping("/paypal/cancel")
    public String cancelPayment(){
        return "paymentCancel";
    }

    @GetMapping("/paypal/error")
    public String cancelError(){
        return "paymentError";
    }
}
