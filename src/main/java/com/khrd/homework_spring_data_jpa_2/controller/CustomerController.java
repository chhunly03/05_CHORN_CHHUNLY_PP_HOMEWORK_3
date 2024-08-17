package com.khrd.homework_spring_data_jpa_2.controller;

import com.khrd.homework_spring_data_jpa_2.model.entity.Customer;
import com.khrd.homework_spring_data_jpa_2.model.request.CustomerDTO;
import com.khrd.homework_spring_data_jpa_2.model.request.CustomerRequest;
import com.khrd.homework_spring_data_jpa_2.model.response.ApiResponse;
import com.khrd.homework_spring_data_jpa_2.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer/")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Integer customer_id) {
        Customer customer = customerService.getCustomerByCustomerId(customer_id);
        ApiResponse<Customer> response = ApiResponse.<Customer>builder()
                .httpStatus(HttpStatus.OK)
                .message("Get customer id " + customer_id + " successfully.")
                .payload(customer)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<CustomerDTO>> createCustomer(@RequestBody CustomerRequest customerRequest) {
        Customer customer = customerService.createCustomer(customerRequest);
        ApiResponse<CustomerDTO> response;

        if (customer != null) {
            response = ApiResponse.<CustomerDTO>builder()
                    .httpStatus(HttpStatus.CREATED)
                    .message("A new customer is inserted successfully.")
                    .payload(CustomerDTO.fromEntity(customer))
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response = ApiResponse.<CustomerDTO>builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("Failed to insert a new customer.")
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Integer customer_id, @RequestBody CustomerRequest customerRequest) {
        Customer customer = customerService.updateCustomer(customer_id, customerRequest);
        ApiResponse<Customer> response;
        if (customer != null) {
            response = ApiResponse.<Customer>builder()
                    .httpStatus(HttpStatus.OK)
                    .message("A new customer is inserted successfully.")
                    .payload(customerService.getCustomerByCustomerId(customer.getId()))
                    .build();
        } else {
            response = ApiResponse.<Customer>builder()
                    .httpStatus(HttpStatus.OK)
                    .message("A new customer is not inserted successfully.")
                    .build();
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Customer> deleteCustomer(@PathVariable("id") Integer customer_id) {
        ApiResponse<Customer> response = ApiResponse.<Customer>builder()
                .httpStatus(HttpStatus.OK)
                .message("Customer id "+customer_id+" is deleted successfully")
                .payload(customerService.DeleteCustomer(customer_id))
                .build();
        return ResponseEntity.ok(response).getBody();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomer(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        List<Customer> customers = customerService.getAllEmployee(pageable);

        ApiResponse<List<Customer>> response = ApiResponse.<List<Customer>>builder()
                .httpStatus(HttpStatus.OK)
                .message("Get all customers successfully.")
                .payload(customers)
                .build();

        return ResponseEntity.ok(response);
    }
}
