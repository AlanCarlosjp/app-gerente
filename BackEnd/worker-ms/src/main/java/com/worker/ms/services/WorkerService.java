package com.worker.ms.services;

import com.worker.ms.dto.WorkerDto;
import com.worker.ms.entity.Worker;
import com.worker.ms.ports.repository.WorkerRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class WorkerService {

    private final WorkerRepository repository;

    public WorkerService(WorkerRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<WorkerDto> findAllPaged(PageRequest pageRequest) {
        Page<Worker> list = repository.findAll(pageRequest);

        return list.map(x -> new WorkerDto(x));
    }

    @Transactional
    public boolean insert(WorkerDto dto) {
        if (dto.getName() != null) {
            Worker entity = copyDtoForEntity(dto);
            repository.save(entity);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public WorkerDto findById(Long id) throws Exception {
        Optional<Worker> obj = repository.findById(id);
        Worker entity = obj.orElseThrow(() -> new Exception("Entity not exist"));
        return new WorkerDto(entity);
    }


    @Transactional
    public WorkerDto update(Long id, WorkerDto dto) throws Exception {
        if (repository.existsById(id)) {
            Worker entity = copyDtoForEntity(dto);
            repository.save(entity);
            return new WorkerDto(entity);
        }
        throw new Exception("ID not exist");
    }

    @Transactional
    public boolean delete(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return true;
            }
            return false;
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Id not exist");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integrity violation");
        }
    }


    private Worker copyDtoForEntity(WorkerDto dto) {
        Worker entity = new Worker();
        entity.setId(dto.getId());
        entity.setDailyIncome(dto.getDailyIncome());
        entity.setName(dto.getName());

        return entity;
    }
}
