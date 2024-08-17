package com.khrd.homework_spring_data_jpa_2.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khrd.homework_spring_data_jpa_2.model.response.ProductResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "product_name")
    private String name;
    @Column(precision = 8,scale = 2,name = "unit_price")
    private BigDecimal unit_price;
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Product_Order>productOrders;
}
