package com.khrd.homework_spring_data_jpa_2.controller;

import com.khrd.homework_spring_data_jpa_2.model.entity.Customer;
import com.khrd.homework_spring_data_jpa_2.model.entity.Product;
import com.khrd.homework_spring_data_jpa_2.model.request.CustomerRequest;
import com.khrd.homework_spring_data_jpa_2.model.request.ProductRequest;
import com.khrd.homework_spring_data_jpa_2.model.response.ApiResponse;
import com.khrd.homework_spring_data_jpa_2.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product/")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProduct(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "productId") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        List<Product> product = productService.getAllProduct(pageable);

        ApiResponse<List<Product>> response = ApiResponse.<List<Product>>builder()
                .httpStatus(HttpStatus.OK)
                .message("Get all product successfully.")
                .payload(product)
                .build();

        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductByID(@PathVariable Integer id) {
        ApiResponse<Product> response;

        try {
            Product product = productService.getProductByID(id);
            response = ApiResponse.<Product>builder()
                    .message("Product retrieved successfully")
                    .httpStatus(HttpStatus.OK)
                    .payload(product)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = ApiResponse.<Product>builder()
                    .message(e.getMessage())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<?>> createProduct(@RequestBody ProductRequest productRequest) {
        Product productID = productService.createProduct(productRequest);
        ApiResponse<?> response = null;
        if (productID != null) {
            response = ApiResponse.<Product>builder()
                    .message("A new product is inserted successfully.")
                    .httpStatus(HttpStatus.CREATED)
                    .payload(productService.getProductByID(productID.getProductId()))
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response = ApiResponse.<String>builder()
                    .message("A new product is not inserted successfully.")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Integer productId, @RequestBody ProductRequest productRequest) {
        Product product = productService.updateProduct(productId,productRequest);
        ApiResponse<Product> response;
        if (product != null) {
            response = ApiResponse.<Product>builder()
                    .httpStatus(HttpStatus.OK)
                    .message("A new product is inserted successfully.")
                    .payload(productService.getProductByID(product.getProductId()))
                    .build();

        } else {
            response = ApiResponse.<Product>builder()
                    .httpStatus(HttpStatus.OK)
                    .message("A new customer is not inserted successfully.")
                    .build();
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Product> deleteProduct(@PathVariable("id") Integer customer_id) {
        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .httpStatus(HttpStatus.OK)
                .message("Product id "+customer_id+" is deleted successfully")
                .payload(productService.DeleteProduct(customer_id))
                .build();
        return ResponseEntity.ok(response).getBody();
    }
}
