package com.khrd.homework_spring_data_jpa_2.repository;

import com.khrd.homework_spring_data_jpa_2.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
