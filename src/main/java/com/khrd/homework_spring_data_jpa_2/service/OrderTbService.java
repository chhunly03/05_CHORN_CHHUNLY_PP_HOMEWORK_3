package com.khrd.homework_spring_data_jpa_2.service;

import com.khrd.homework_spring_data_jpa_2.controller.OrderStatus;
import com.khrd.homework_spring_data_jpa_2.model.entity.Order_tb;
import com.khrd.homework_spring_data_jpa_2.model.request.OrderTbRequest;
import com.khrd.homework_spring_data_jpa_2.model.response.OrderResponseDto;

import java.util.List;

public interface OrderTbService {
    List<OrderResponseDto> getOrdersByCustomerId(Integer customerId);

    Order_tb createOrderByCustomer(Integer customerId, List<OrderTbRequest> orderTbRequests);

    Order_tb findOrderByID(Integer orderId);

    Order_tb updateOrderStatus(Integer orderId, OrderStatus status);
}
