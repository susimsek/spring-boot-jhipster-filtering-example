package io.susimsek.filteringexample.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto implements Serializable {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Long id;

    @NotBlank
    String name;

    BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDto)) {
            return false;
        }

        ProductDto productDto = (ProductDto) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
