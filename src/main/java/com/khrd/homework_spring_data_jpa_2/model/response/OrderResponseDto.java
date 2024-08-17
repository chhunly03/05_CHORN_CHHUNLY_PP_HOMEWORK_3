package com.khrd.homework_spring_data_jpa_2.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.khrd.homework_spring_data_jpa_2.controller.OrderStatus;
import com.khrd.homework_spring_data_jpa_2.model.entity.Order_tb;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Integer orderId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private List<ProductResponseDto> products;

    public static OrderResponseDto fromOrder(Order_tb order) {
        List<ProductResponseDto> productDtos = order.getProductList().stream()
                .map(productOrder -> ProductResponseDto.fromProduct(productOrder.getProduct()))
                .collect(Collectors.toList());

        return new OrderResponseDto(
                order.getOrder_id(),
                order.getOrder_date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime(),
                order.getTotal_amount(),
                order.getStatus(),
                productDtos
        );
    }
}
