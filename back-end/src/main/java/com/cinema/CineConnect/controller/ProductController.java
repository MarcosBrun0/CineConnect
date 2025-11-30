package com.cinema.CineConnect.controller;

import com.cinema.CineConnect.model.DTO.ProductRecord;
import com.cinema.CineConnect.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductRecord> findAll() {
        return productRepository.findAll();
    }
}
