package com.pay.roll.controller;

import com.pay.roll.dto.PaymentDto;
import com.pay.roll.dto.WorkerDto;
import com.pay.roll.entity.Payment;
import com.pay.roll.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }


    @GetMapping(value = "/{id}/days/{days}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable Long id, @PathVariable Integer days) throws Exception {
       PaymentDto payment = service.getPayment(id, days);
        return ResponseEntity.ok().body(payment);
    }


}
