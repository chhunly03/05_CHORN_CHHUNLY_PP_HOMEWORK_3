package com.khrd.homework_spring_data_jpa_2.controller;

import com.khrd.homework_spring_data_jpa_2.model.entity.Order_tb;
import com.khrd.homework_spring_data_jpa_2.model.request.OrderTbRequest;
import com.khrd.homework_spring_data_jpa_2.model.response.ApiResponse;
import com.khrd.homework_spring_data_jpa_2.model.response.OrderResponseDto;
import com.khrd.homework_spring_data_jpa_2.service.OrderTbService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order/")
public class OrderTbController {
    private final OrderTbService orderTbService;

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> getOrdersByCustomer(
            @PathVariable("customerId") Integer customerId) {

        List<OrderResponseDto> orderResponseDtos = orderTbService.getOrdersByCustomerId(customerId);
        ApiResponse<List<OrderResponseDto>> response;

        if (!orderResponseDtos.isEmpty()) {
            response = ApiResponse.<List<OrderResponseDto>>builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Get all orders with customer id " + customerId + " successfully.")
                    .payload(orderResponseDtos)
                    .build();
        } else {
            response = ApiResponse.<List<OrderResponseDto>>builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("No orders found for customer id " + customerId + ".")
                    .build();
        }

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> createOrderByCustomer(
            @PathVariable("customerId") Integer customerId,
            @RequestBody List<OrderTbRequest> orderTbRequests) {

        Order_tb order = orderTbService.createOrderByCustomer(customerId, orderTbRequests);

        OrderResponseDto orderResponseDto = OrderResponseDto.fromOrder(order);

        ApiResponse<OrderResponseDto> response;

        if (order != null) {
            response = ApiResponse.<OrderResponseDto>builder()
                    .httpStatus(HttpStatus.CREATED)
                    .message("A new order is created successfully.")
                    .payload(orderResponseDto)
                    .build();
        } else {
            response = ApiResponse.<OrderResponseDto>builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("Failed to create a new order.")
                    .build();
        }

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> getOrderById(@PathVariable("orderId") Integer order_id) {
        Order_tb order_tb = orderTbService.findOrderByID(order_id);
        OrderResponseDto orderResponseDto = OrderResponseDto.fromOrder(order_tb);
        ApiResponse<OrderResponseDto> response = ApiResponse.<OrderResponseDto>builder()
                .httpStatus(HttpStatus.OK)
                .message("Get customer id " + order_id + " successfully.")
                .payload(orderResponseDto)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/status")
    public ResponseEntity<ApiResponse<OrderResponseDto>> updateOrderStatus(
            @RequestParam("status") OrderStatus status,
            @RequestParam("orderId") Integer orderId)
    {
        Order_tb order = orderTbService.updateOrderStatus(orderId, status);
        OrderResponseDto orderResponseDto = OrderResponseDto.fromOrder(order);
        ApiResponse<OrderResponseDto> response;

        if (order != null) {
            response = ApiResponse.<OrderResponseDto>builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Order status updated successfully.")
                    .payload(orderResponseDto)
                    .build();
        } else {
            response = ApiResponse.<OrderResponseDto>builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("Order not found.")
                    .build();
        }

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }


}
