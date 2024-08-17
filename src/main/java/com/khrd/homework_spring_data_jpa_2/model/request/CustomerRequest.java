package com.khrd.homework_spring_data_jpa_2.model.request;
import com.khrd.homework_spring_data_jpa_2.model.entity.Customer;
import com.khrd.homework_spring_data_jpa_2.model.entity.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerRequest {
    private String customer_name;
    private String address;
    private String phone_number;
    private String email;

    public Customer toCustomerDTO() {
        Email e = new Email(null,this.email,null);
        return new Customer(null, this.customer_name, this.address, this.phone_number,e,null);
    }


    public Customer toUpdateCustomerDTO(Integer id) {
        Email e = new Email(id,this.email,null);
        return new Customer( id,this.customer_name, this.address, this.phone_number,e,null);
    }

    public Customer toCustomer() {
        Customer customer = new Customer();
        customer.setName(this.customer_name);
        customer.setAddress(this.address);
        customer.setPhone_number(this.phone_number);

        Email emailEntity = new Email();
        emailEntity.setEmail(this.email);
        customer.setEmail(emailEntity);
        return customer;
    }
}
