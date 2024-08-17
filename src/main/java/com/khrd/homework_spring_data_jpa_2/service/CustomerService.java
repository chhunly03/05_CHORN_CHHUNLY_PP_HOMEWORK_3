package com.khrd.homework_spring_data_jpa_2.service;

import com.khrd.homework_spring_data_jpa_2.model.entity.Customer;
import com.khrd.homework_spring_data_jpa_2.model.request.CustomerRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    

    Customer getCustomerByCustomerId(Integer customer_id);

    Customer createCustomer(CustomerRequest customerRequest);

    Customer updateCustomer(Integer customerId, CustomerRequest customerRequest);

    Customer DeleteCustomer(Integer customerId);

    List<Customer> getAllEmployee(Pageable pageable);
}
