package com.ecommerce.sample.service;

import com.ecommerce.sample.model.Category;
import com.ecommerce.sample.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public void createCategory(Category category) {
        categoryRepo.save(category);
    }

    public List<Category> getAll() {
        return categoryRepo.findAll();
    }

    public void editCategory(Integer catid, Category updatecategory) {
        Category category = categoryRepo.getById(catid);
        category.setCategoryName(updatecategory.getCategoryName());
        category.setDescription(updatecategory.getDescription());
        category.setImageUrl(updatecategory.getImageUrl());
        categoryRepo.save(category);
    }

    public boolean findById(int catId) {
        return categoryRepo.findById(catId).isPresent();
    }
}

