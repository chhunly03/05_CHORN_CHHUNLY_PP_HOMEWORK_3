package com.khrd.homework_spring_data_jpa_2.model.request;

import com.khrd.homework_spring_data_jpa_2.model.entity.Customer;
import com.khrd.homework_spring_data_jpa_2.model.entity.Email;
import com.khrd.homework_spring_data_jpa_2.model.entity.Order_tb;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
    private Email email;
    private List<Order_tb>order_tbs;

    public CustomerDTO(Integer id, String name, String address, String phoneNumber, Email email) {
    }

    public static CustomerDTO fromEntity(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getPhone_number(),
                customer.getEmail()
        );
    }
}
