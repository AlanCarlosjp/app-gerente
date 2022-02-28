package com.api.gateway.feign;


import com.api.gateway.dto.WorkerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@Component
@FeignClient(name = "worker",path = "/worker")
public interface WorkerFeignCliente {

    @GetMapping(value = "/{id}")
    ResponseEntity<WorkerDto> findById(@PathVariable(value = "id") Long id) throws Exception;

    @GetMapping
     ResponseEntity<Page<WorkerDto>> findAll(
            @RequestParam(value = "pages",defaultValue = "0") Integer pages,
            @RequestParam(value = "linesPerPage",defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "name") String orderBy,
            @RequestParam(value = "direction ",defaultValue = "ASC") String direction);

    @PostMapping
     ResponseEntity insertWorker(@RequestBody WorkerDto dto);

    @PutMapping(value = "/{id}")
     ResponseEntity<WorkerDto> update(@PathVariable(value = "id") Long id,@RequestBody WorkerDto dto) throws Exception;

    @DeleteMapping(value = "/{id}")
     ResponseEntity<WorkerDto> delete(@PathVariable(value = "id") Long id);
}
