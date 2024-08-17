package com.khrd.homework_spring_data_jpa_2.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khrd.homework_spring_data_jpa_2.controller.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Order_tb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date order_date;
    private BigDecimal total_amount;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "order_tb",cascade = CascadeType.ALL)
    private List<Product_Order>productList;

    public Order_tb(Integer quantity, Integer productId) {
    }
}
