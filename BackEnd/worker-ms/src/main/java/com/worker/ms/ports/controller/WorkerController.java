package com.worker.ms.ports.controller;

import com.worker.ms.dto.WorkerDto;
import com.worker.ms.services.WorkerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/worker")
public class WorkerController {

    private final WorkerService service;

    public WorkerController(WorkerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<WorkerDto>> findAll(
            @RequestParam(value = "pages",defaultValue = "0") Integer pages,
            @RequestParam(value = "linesPerPage",defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "name") String orderBy,
            @RequestParam(value = "direction ",defaultValue = "ASC") String direction
    ){
        PageRequest pageRequest =  PageRequest.of(pages,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        Page<WorkerDto> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity insertWorker(@RequestBody WorkerDto dto) {
        boolean inserted = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        if(inserted == false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Worker was not inserted");
        }
        return ResponseEntity.created(uri).body(inserted);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkerDto> findById(@PathVariable(value = "id") Long id) throws Exception {
        WorkerDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<WorkerDto> update(@PathVariable(value = "id") Long id,@RequestBody WorkerDto dto) throws Exception {
        dto = service.update(id,dto);
        return ResponseEntity.ok().body(dto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<WorkerDto> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
