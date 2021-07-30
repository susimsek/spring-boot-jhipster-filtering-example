package io.susimsek.filteringexample.service.impl;


import io.susimsek.filteringexample.domain.Product;
import io.susimsek.filteringexample.errors.ResourceNotFoundException;
import io.susimsek.filteringexample.repository.ProductRepository;
import io.susimsek.filteringexample.service.ProductService;
import io.susimsek.filteringexample.service.dto.ProductDto;
import io.susimsek.filteringexample.service.mapper.ProductMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
@Transactional
public class ProductServiceImpl implements ProductService {


    final ProductRepository productRepository;

    final ProductMapper productMapper;

    @Override
    public ProductDto save(ProductDto productDto) {
        log.debug("Request to save Product : {}", productDto);
        Product product = productMapper.toEntity(productDto);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product", "id", id);
        }
        productDto.setId(id);

        Product product = productMapper.toEntity(productDto);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public Optional<ProductDto> partialUpdate(ProductDto productDto) {
        log.debug("Request to partially update Product : {}", productDto);

        Optional<Product> result =  productRepository.findById(productDto.getId());

        if (result.isEmpty()){
            throw new ResourceNotFoundException("Product", "id", productDto.getId());
        }

        return result
            .map(
                existingProduct -> {
                    productMapper.partialUpdate(existingProduct, productDto);

                    return existingProduct;
                }
            )
            .map(productRepository::save)
            .map(productMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        return productRepository.findAll(pageable).map(productMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDto> findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        Optional<Product> result =  productRepository.findById(id);

        if (result.isEmpty()){
            throw new ResourceNotFoundException("Product", "id", id);
        }

        return result.map(productMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);

        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product", "id", id);
        }


        productRepository.deleteById(id);
    }
}
