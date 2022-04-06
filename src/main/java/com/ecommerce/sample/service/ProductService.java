package com.ecommerce.sample.service;

import com.ecommerce.sample.dto.ProductDto;
import com.ecommerce.sample.model.Category;
import com.ecommerce.sample.model.Product;
import com.ecommerce.sample.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;


    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setProoductDescription(productDto.getProoductDescription());
        product.setProoductName(productDto.getProoductName());
        product.setImageUrl(productDto.getImageUrl());
        product.setProoductPrice(productDto.getProoductPrice());
        product.setCategory(category);
        productRepo.save(product);
    }

    public ProductDto getProductDto (Product product){
        ProductDto productDto = new ProductDto();
        productDto.setProoductDescription(product.getProoductDescription());
        productDto.setProoductName(product.getProoductName());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setProoductPrice(product.getProoductPrice());
        productDto.setCategoryid(product.getCategory().getId());
        productDto.setProoductid(product.getProoductid());
        return productDto;
    }

    public List<ProductDto> getAll() {
        List<Product> allproducts = productRepo.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product:allproducts){
            productDtos.add(getProductDto(product) );
        }

        return productDtos;
    }

    public void updateProduct(ProductDto productDto, Integer productid) throws Exception {
        Optional<Product> optionalProduct = productRepo.findById(productid);

        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();

            product.setProoductDescription(productDto.getProoductDescription());
            product.setProoductName(productDto.getProoductName());
            product.setImageUrl(productDto.getImageUrl());
            product.setProoductPrice(productDto.getProoductPrice());
            productRepo.save(product);
        }else {
            throw new Exception("product not present");
        }

    }

    public Product findById(Integer productid) throws Exception {
        Optional<Product> productbyId = productRepo.findById(productid);
        if (productbyId.isEmpty()){
            throw new Exception("invalid product id");
        }
            return productbyId.get();

    }
}
