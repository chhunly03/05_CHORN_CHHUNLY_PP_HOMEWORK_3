package com.khrd.homework_spring_data_jpa_2.ServiceImplement;

import com.khrd.homework_spring_data_jpa_2.model.entity.Product;
import com.khrd.homework_spring_data_jpa_2.model.request.ProductRequest;
import com.khrd.homework_spring_data_jpa_2.repository.ProductRepository;
import com.khrd.homework_spring_data_jpa_2.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductImplement implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public Product createProduct(ProductRequest productRequest) {
        System.out.println(productRequest.toProduct().toString());
        return productRepository.save(productRequest.toProduct());
    }

    @Override
    public Product getProductByID(Integer id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }

    @Override
    public Product updateProduct(Integer productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId).orElseThrow();
        productRepository.save(productRequest.toUpdateProduct(productId));
        return product;
    }

    @Override
    public Product DeleteProduct(Integer customerId) {
        productRepository.deleteById(customerId);
        return null;
    }
}
