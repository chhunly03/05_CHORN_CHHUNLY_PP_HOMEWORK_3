package com.khrd.homework_spring_data_jpa_2.repository;

import com.khrd.homework_spring_data_jpa_2.model.entity.Order_tb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderTbRepository extends JpaRepository<Order_tb, Integer> {
    List<Order_tb> findByCustomerId(Integer customerId);
}
