package com.ms.product.services;

import com.ms.product.dto.ProductDto;
import com.ms.product.entity.Product;
import com.ms.product.ports.repository.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAllPaged(PageRequest pageRequest) {
        Page<Product> list = repository.findAll(pageRequest);

        return list.map(x -> new ProductDto(x));
    }

    @Transactional
    public boolean insert(ProductDto dto) {
        if (dto.getName() != null) {
            Product entity = copyDtoForEntity(dto);
            repository.save(entity);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) throws Exception {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new Exception("Entity not exist"));
        return new ProductDto(entity);
    }


    @Transactional
    public ProductDto update(Long id, ProductDto dto) throws Exception {
        if (repository.existsById(id)) {
            Product entity = copyDtoForEntity(dto);
            repository.save(entity);
            return new ProductDto(entity);
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


    private Product copyDtoForEntity(ProductDto dto) {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setDailyIncome(dto.getDailyIncome());
        entity.setName(dto.getName());

        return entity;
    }
}
