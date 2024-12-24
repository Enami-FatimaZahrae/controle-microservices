package com.controle.productservice.controller;

import com.controle.productservice.dto.ProductRequest;
import com.controle.productservice.dto.ProductResponse;
import com.controle.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @QueryMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @MutationMapping
    public boolean createProduct(ProductRequest productRequest) {
        productService.createProduct(productRequest);
        return true;
    }

    @MutationMapping
    public boolean updateProduct(@Argument Long id, @Argument ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest);
    }

    // Mutation pour supprimer un produit
    @MutationMapping
    public boolean deleteProduct(@Argument Long id) {
        return productService.deleteProduct(id);
    }


}
