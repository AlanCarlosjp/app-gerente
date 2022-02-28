package com.pay.roll.config;

import com.pay.roll.dto.WorkerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "worker",path = "/worker")
public interface WorkerFeignCliente {

    @GetMapping(value = "/{id}")
    ResponseEntity<WorkerDto> findById(@PathVariable(value = "id") Long id) throws Exception;
}
