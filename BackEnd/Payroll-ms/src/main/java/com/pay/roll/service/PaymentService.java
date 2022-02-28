package com.pay.roll.service;

import com.pay.roll.config.WorkerFeignCliente;
import com.pay.roll.dto.PaymentDto;
import com.pay.roll.dto.WorkerDto;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final WorkerFeignCliente feign;

    public PaymentService(WorkerFeignCliente feign) {
        this.feign = feign;
    }

    public PaymentDto getPayment(long workerId, int days) throws Exception {
        WorkerDto dto = feign.findById(workerId).getBody();
        return new PaymentDto(dto.getName(), dto.getDailyIncome(), days);
    }
}
