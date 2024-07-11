package com.E_commerce.E_commerce.controllers;

import com.E_commerce.E_commerce.entities.Products;
import com.E_commerce.E_commerce.services.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductsController {

        @Autowired
        private ProductService productService;

        @GetMapping
        public ResponseEntity<List<Products>> getAllProducts() {
            List<Products> products = productService.searchAll();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Products> getProductById(@PathVariable Long id) {
            Products product = productService.searchById(id);
            if (product != null) {
                return new ResponseEntity<>(product, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping
        public ResponseEntity<Products> createProduct(@RequestBody Products product) {
            Products createdProduct = productService.create(product);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products updatedProduct) {
            try {
                Products product = productService.updateProduct(id, updatedProduct);
                return new ResponseEntity<>(product, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
            try {
                productService.deleteProduct(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
}
