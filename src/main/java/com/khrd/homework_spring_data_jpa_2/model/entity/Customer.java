package com.khrd.homework_spring_data_jpa_2.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;

    @Column(name = "customer_name", nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone_number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email_id")
    private Email email;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Order_tb>OrderList;
}
