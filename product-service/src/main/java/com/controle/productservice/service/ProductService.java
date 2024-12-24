package com.controle.productservice.service;

import com.controle.productservice.dto.ProductRequest;
import com.controle.productservice.dto.ProductResponse;
import com.controle.productservice.model.Product;
import com.controle.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public boolean updateProduct(Long productId, ProductRequest productRequest) {
        Optional<Product> existingProductOpt = productRepository.findById(String.valueOf(productId));

        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();

            existingProduct.setName(productRequest.getName());
            existingProduct.setDescription(productRequest.getDescription());
            existingProduct.setPrice(productRequest.getPrice());

            productRepository.save(existingProduct);
            log.info("Product {} is updated", existingProduct.getId());
        } else {
            log.error("Product with id {} not found for update", productId);
            throw new RuntimeException("Product not found");
        }
        return false;
    }

    public boolean deleteProduct(Long productId) {
        Optional<Product> existingProductOpt = productRepository.findById(String.valueOf(productId));

        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            productRepository.delete(existingProduct);
            log.info("Product {} is deleted", existingProduct.getId());
        } else {
            log.error("Product with id {} not found for deletion", productId);
            throw new RuntimeException("Product not found");
        }
        return true;
    }
}
