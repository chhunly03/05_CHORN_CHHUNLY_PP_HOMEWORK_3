package com.khrd.homework_spring_data_jpa_2.model.response;

import com.khrd.homework_spring_data_jpa_2.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Integer productId;
    private String productName;
    private BigDecimal unitPrice;
    private String description;

    public static ProductResponseDto fromProduct(Product product) {
        return new ProductResponseDto(
                product.getProductId(),
                product.getName(),
                product.getUnit_price(),
                product.getDescription()
        );
    }
}
