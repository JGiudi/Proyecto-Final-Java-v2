package com.E_commerce.E_commerce.services;

import com.E_commerce.E_commerce.entities.Products;
import com.E_commerce.E_commerce.repositories.ProductRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Products create(Products product) {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new ServiceException("Error creating product", e);
        }
    }

    public List<Products> searchAll() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error retrieving products", e);
        }
    }

    public Products searchById(Long id) {
        Optional<Products> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    public Products updateProduct(Long id, Products updateProduct) {
        Optional<Products> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Products productExist = productOptional.get();
            productExist.setName(updateProduct.getName());
            productExist.setPrice(updateProduct.getPrice());
            productExist.setStock(updateProduct.getStock());
            try {
                return productRepository.save(productExist);
            } catch (Exception e) {
                throw new ServiceException("Error updating product", e);
            }
        } else {
            throw new ServiceException("Product not found with that id");
        }
    }
    public void deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Error deleting product", e);
        }
    }
}
