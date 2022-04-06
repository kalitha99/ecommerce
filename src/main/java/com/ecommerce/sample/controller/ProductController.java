package com.ecommerce.sample.controller;

import com.ecommerce.sample.common.ApiResponse;
import com.ecommerce.sample.dto.ProductDto;
import com.ecommerce.sample.model.Category;
import com.ecommerce.sample.model.Product;
import com.ecommerce.sample.repository.CategoryRepo;
import com.ecommerce.sample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping("/all")

    public ResponseEntity<List<ProductDto> >getAllCategories(){
        List<ProductDto> products = productService.getAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/add")
    //@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<com.ecommerce.sample.common.ApiResponse> createProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryid());
        if (optionalCategory.isPresent()) {
            productService.createProduct(productDto, optionalCategory.get());
            return new ResponseEntity<ApiResponse>(new com.ecommerce.sample.common.ApiResponse(true, "added"), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<ApiResponse>(new com.ecommerce.sample.common.ApiResponse(false, "category doesnt exist"), HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/edit/{productid}")
   // @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<com.ecommerce.sample.common.ApiResponse> editProduct(@PathVariable("productid") Integer productid,@RequestBody ProductDto productDto) throws Exception {
        Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryid());
        if (optionalCategory.isPresent()) {
            productService.updateProduct(productDto, productid);
            return new ResponseEntity<ApiResponse>(new com.ecommerce.sample.common.ApiResponse(true, "added"), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<ApiResponse>(new com.ecommerce.sample.common.ApiResponse(false, "category doesnt exist"), HttpStatus.BAD_REQUEST);

        }
    }

}
