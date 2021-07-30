package io.susimsek.filteringexample.service.query;


import io.susimsek.filteringexample.domain.Product;
import io.susimsek.filteringexample.domain.Product_;
import io.susimsek.filteringexample.repository.ProductRepository;
import io.susimsek.filteringexample.service.criteria.ProductCriteria;
import io.susimsek.filteringexample.service.dto.ProductDto;
import io.susimsek.filteringexample.service.mapper.ProductMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import java.util.List;


@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductQueryService extends QueryService<Product> {

    final ProductRepository productRepository;

    final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public List<ProductDto> findByCriteria(ProductCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Product> specification = createSpecification(criteria);
        return productMapper.toDto(productRepository.findAll(specification));
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findByCriteria(ProductCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Product> specification = createSpecification(criteria);
        return productRepository.findAll(specification, page).map(productMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(ProductCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Product> specification = createSpecification(criteria);
        return productRepository.count(specification);
    }


    protected Specification<Product> createSpecification(ProductCriteria criteria) {
        Specification<Product> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Product_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Product_.name));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), Product_.price));
            }
        }
        return specification;
    }
}
