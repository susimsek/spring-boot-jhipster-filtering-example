package io.susimsek.filteringexample.controller.rest;


import io.susimsek.filteringexample.service.ProductService;
import io.susimsek.filteringexample.service.criteria.ProductCriteria;
import io.susimsek.filteringexample.service.dto.ProductDto;
import io.susimsek.filteringexample.service.query.ProductQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@Tag(name = "product", description = "Product API")
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {

    final ProductService productService;
    final ProductQueryService productQueryService;


    @Operation(summary = "Add a new product", description = "", tags = { "product" })
    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) throws URISyntaxException {
        log.debug("REST request to save Product : {}", productDto);

        ProductDto result = productService.save(productDto);
        return ResponseEntity
            .created(new URI("/api/products/" + result.getId()))
            .body(result);
    }


    @Operation(summary = "Update an existing product", description = "", tags = { "product" })
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProductDto productDto
    ) throws URISyntaxException {
        log.debug("REST request to update Product : {}, {}", id, productDto);

        ProductDto result = productService.update(id, productDto);
        return ResponseEntity
            .ok()
            .body(result);
    }

    @Operation(summary = "Partial update an existing product partially", description = "", tags = { "product" })
    @PatchMapping(value = "/products/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<ProductDto> partialUpdateProduct(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ProductDto productDto
    ) throws URISyntaxException {
        log.debug("REST request to partial update Product partially : {}, {}", id, productDto);

        Optional<ProductDto> result = productService.partialUpdate(productDto);

        return ResponseUtil.wrapOrNotFound(
            result
        );
    }

    @Operation(summary = "Find all products", description = "Returns product list", tags = { "contact" })
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(@ParameterObject ProductCriteria criteria, @ParameterObject Pageable pageable) {
        log.debug("REST request to get Products by criteria: {}", criteria);
        Page<ProductDto> page = productQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @Operation(summary = "Find product by ID", description = "Returns a single product", tags = { "product" })
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        log.debug("REST request to get Product : {}", id);
        Optional<ProductDto> productDto = productService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productDto);
    }


    @Operation(summary = "Deletes a product", description = "", tags = { "product" })
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("REST request to delete Product : {}", id);
        productService.delete(id);

        return ResponseEntity
            .noContent()
            .build();
    }
}
