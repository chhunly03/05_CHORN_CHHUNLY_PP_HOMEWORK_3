package com.khrd.homework_spring_data_jpa_2.ServiceImplement;

import com.khrd.homework_spring_data_jpa_2.controller.OrderStatus;
import com.khrd.homework_spring_data_jpa_2.model.entity.Customer;
import com.khrd.homework_spring_data_jpa_2.model.entity.Order_tb;
import com.khrd.homework_spring_data_jpa_2.model.entity.Product;
import com.khrd.homework_spring_data_jpa_2.model.entity.Product_Order;
import com.khrd.homework_spring_data_jpa_2.model.request.OrderTbRequest;
import com.khrd.homework_spring_data_jpa_2.model.response.OrderResponseDto;
import com.khrd.homework_spring_data_jpa_2.repository.CustomerRepository;
import com.khrd.homework_spring_data_jpa_2.repository.OrderTbRepository;
import com.khrd.homework_spring_data_jpa_2.repository.ProductRepository;
import com.khrd.homework_spring_data_jpa_2.service.OrderTbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderTbImplement implements OrderTbService {

    private final OrderTbRepository orderTbRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderResponseDto> getOrdersByCustomerId(Integer customerId) {
        List<Order_tb> orders = orderTbRepository.findByCustomerId(customerId);
        if (orders.isEmpty()) {
            return new ArrayList<>();
        }
        return orders.stream()
                .map(OrderResponseDto::fromOrder)
                .collect(Collectors.toList());
    }

    @Override
    public Order_tb createOrderByCustomer(Integer customerId, List<OrderTbRequest> orderTbRequests) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return null;
        }
        Order_tb order = new Order_tb();
        order.setOrder_date(new Date());
        order.setStatus(OrderStatus.PENDING);

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<Product_Order> productOrders = new ArrayList<>();
        for (OrderTbRequest request : orderTbRequests) {
            Product product = productRepository.findById(request.getProductId()).orElse(null);
            if (product != null) {
                BigDecimal unitPrice = product.getUnit_price();
                Integer quantity = request.getQuantity();
                BigDecimal itemTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));

                // Add to total amount
                totalAmount = totalAmount.add(itemTotal);

                // Create Product_Order
                Product_Order productOrder = new Product_Order();
                productOrder.setProduct(product);
                productOrder.setQuantity(quantity);
                productOrder.setOrder_tb(order);
                productOrders.add(productOrder);
            }
        }

        order.setTotal_amount(totalAmount);
        order.setProductList(productOrders);

        order.setCustomer(customer);

        return orderTbRepository.save(order);
    }

    @Override
    public Order_tb findOrderByID(Integer orderId) {
        return orderTbRepository.findById(orderId).orElse(null);
    }

    @Override
    public Order_tb updateOrderStatus(Integer orderId, OrderStatus status) {
        Order_tb order_tb = orderTbRepository.findById(orderId).orElse(null);
        if (order_tb == null) {
            return null;
        } else {
            order_tb.setStatus(status);
        }
        return order_tb;
    }
}
