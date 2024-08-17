package com.khrd.homework_spring_data_jpa_2.ServiceImplement;

import com.khrd.homework_spring_data_jpa_2.model.entity.Customer;
import com.khrd.homework_spring_data_jpa_2.model.request.CustomerDTO;
import com.khrd.homework_spring_data_jpa_2.model.request.CustomerRequest;
import com.khrd.homework_spring_data_jpa_2.repository.CustomerRepository;
import com.khrd.homework_spring_data_jpa_2.service.CustomerService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerImplement implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerImplement(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomerByCustomerId(Integer customerId) {
        return customerRepository.findById(customerId).orElseThrow();
    }

    @Override
    public Customer createCustomer(CustomerRequest customerRequest) {
        return customerRepository.save(customerRequest.toCustomer());
    }

    @Override
    public Customer updateCustomer(Integer customerId, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        customerRepository.save(customerRequest.toUpdateCustomerDTO(customerId));
        return customer;
    }

    @Override
    public Customer DeleteCustomer(Integer customerId) {
        customerRepository.deleteById(customerId);
        return null;
    }

    @Override
    public List<Customer> getAllEmployee(Pageable pageable) {
        return customerRepository.findAll(pageable).getContent();
    }
}
