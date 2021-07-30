package io.susimsek.filteringexample.service;

import io.susimsek.filteringexample.service.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ProductService {
    ProductDto save(ProductDto productDto);

    ProductDto update(Long id, ProductDto productDto);

    Optional<ProductDto> partialUpdate(ProductDto productDto);

    Page<ProductDto> findAll(Pageable pageable);

    Optional<ProductDto> findOne(Long id);

    void delete(Long id);
}
