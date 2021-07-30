package io.susimsek.filteringexample.service.mapper;


import io.susimsek.filteringexample.domain.Product;
import io.susimsek.filteringexample.service.dto.ProductDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDto}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductMapper extends EntityMapper<ProductDto, Product> {}
