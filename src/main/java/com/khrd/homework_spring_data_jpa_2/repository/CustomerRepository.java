package com.khrd.homework_spring_data_jpa_2.repository;

import com.khrd.homework_spring_data_jpa_2.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
