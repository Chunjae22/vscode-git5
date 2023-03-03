package com.springboot.jpa.data.dao.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.entity.ProductRepository;

public class ProductDAOImpl implements ProductDAO {
    private final ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        Product selectedProduct = productRepository.getReferenceById(number);
        return selectedProduct;
    }

    @Override
    public Product updateProduct(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            throw new Exception();
        }

        Product updatedProduct;
        Product product = selectedProduct.get();
        product.setName(name);
        product.setUpdatedAt(LocalDateTime.now());

        updatedProduct = productRepository.save(product);
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        if(selectedProduct.isPresent()) {
            throw new Exception();
        }

        Product product = selectedProduct.get();
        productRepository.delete(product);
    }
    
}
