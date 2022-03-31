package com.ecommerce.sample.service;

import com.ecommerce.sample.dto.ProductDto;
import com.ecommerce.sample.model.User;
import com.ecommerce.sample.model.WishList;
import com.ecommerce.sample.repository.WishListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    @Autowired
    WishListRepo wishListRepo;

    @Autowired
    ProductService productService;

    public void createWishList(WishList wishList) {
        wishListRepo.save(wishList);
    }

    public List<ProductDto> getall(User user) {
       final List<WishList> wishlists = wishListRepo.findAllByUser(user);
       List<ProductDto> productDtos = new ArrayList<>();
       for (WishList wishList: wishlists){
           productDtos.add(productService.getProductDto(wishList.getProduct()));
       }
       return productDtos;
    }
}
