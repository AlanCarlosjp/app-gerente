package com.api.gateway.controller;

import com.api.gateway.feign.PayrollFeignCliente;
import com.api.gateway.dto.PaymentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payroll")
public class ApiPayrollController {

    public final PayrollFeignCliente feignCliente;

    public ApiPayrollController(PayrollFeignCliente feignCliente) {
        this.feignCliente = feignCliente;
    }


    @GetMapping(value = "/{id}/days/{days}")
    public ResponseEntity getPayment(@PathVariable Long id, @PathVariable int days) throws Exception {
        PaymentDto dto = feignCliente.getPayment(id, days).getBody();
        return ResponseEntity.ok().body(dto);
    }

}
