package com.khrd.homework_spring_data_jpa_2.model.request;

import com.khrd.homework_spring_data_jpa_2.model.entity.Product;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {
    @Column(name = "product_name")
    private String name;
    @Column(precision = 8,scale = 2,name = "unit_price")
    private BigDecimal unit_price;
    @Column(name = "description")
    private String description;

    public Product toProduct(){
        return new Product(null,this.name,this.unit_price,this.description,null);
    }

    public Product toUpdateProduct(Integer id){
        return new Product(id,this.name,this.unit_price,this.description,null);
    }
}
