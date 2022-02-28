package com.api.gateway.controller;

import com.api.gateway.feign.WorkerFeignCliente;
import com.api.gateway.dto.WorkerDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/worker")
public class ApiWorkerGetway {

    private final WorkerFeignCliente feignCliente;

    public ApiWorkerGetway(WorkerFeignCliente feignCliente) {
        this.feignCliente = feignCliente;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws Exception {
        WorkerDto dto = feignCliente.findById(id).getBody();
        return ResponseEntity.ok().body(dto);
    }


    @GetMapping
    public ResponseEntity findAll( @RequestParam(value = "pages",defaultValue = "0") Integer pages,
                                   @RequestParam(value = "linesPerPage",defaultValue = "12") Integer linesPerPage,
                                   @RequestParam(value = "orderBy",defaultValue = "name") String orderBy,
                                   @RequestParam(value = "direction ",defaultValue = "ASC") String direction){
       Page<WorkerDto> dtos = feignCliente.findAll(pages, linesPerPage, orderBy, direction).getBody();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity postWorker(@RequestBody WorkerDto dto){
        feignCliente.insertWorker(dto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        feignCliente.delete(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity putById(@PathVariable Long id, @RequestBody WorkerDto dto) throws Exception {
        feignCliente.update(id, dto);
        return ResponseEntity.ok().build();
    }

}
