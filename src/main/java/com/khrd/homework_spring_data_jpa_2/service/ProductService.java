package com.khrd.homework_spring_data_jpa_2.service;

import com.khrd.homework_spring_data_jpa_2.model.entity.Product;
import com.khrd.homework_spring_data_jpa_2.model.request.ProductRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product createProduct(ProductRequest productRequest);

    Product getProductByID(Integer id);

    List<Product> getAllProduct(Pageable pageable);

    Product updateProduct(Integer productId, ProductRequest productRequest);

    Product DeleteProduct(Integer customerId);
}
