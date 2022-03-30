package com.ecommerce.sample.controller;

import com.ecommerce.sample.model.Category;
import com.ecommerce.sample.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
  
    public List<Category> getAllCategories(){
       return categoryService.getAll();
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('Admin')")
    public String createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return "sucess";
    }

    @PostMapping("/update/{catId}")
    @PreAuthorize("hasRole('Admin')")
    public String updateCategory(@PathVariable("catId") int catId,@RequestBody Category category){
        if (categoryService.findById(catId)){
            categoryService.editCategory(catId,category);
            return "sucess";
        }else{
            return "Not valid Id";
        }

    }
}
