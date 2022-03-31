package com.ecommerce.sample.controller;

import com.ecommerce.sample.common.ApiResponse;
import com.ecommerce.sample.dto.ProductDto;
import com.ecommerce.sample.model.Product;
import com.ecommerce.sample.model.User;
import com.ecommerce.sample.model.WishList;
import com.ecommerce.sample.repository.UserDao;
import com.ecommerce.sample.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    WishListService wishListService;

    @Autowired
    UserDao userDao;

    @GetMapping("/{username}")
    public ResponseEntity<List<ProductDto>> ViewAllWishList( @PathVariable("username") String uname) {
        User user = userDao.findById(uname).get();
        System.out.println(user);
        List<ProductDto> allprodforuser = wishListService.getall(user);
        return new ResponseEntity<>(allprodforuser,HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product, @RequestParam("uname") String uname) {
        User user = userDao.findById(uname).get();
        WishList wishList = new WishList(user,product);
        wishListService.createWishList(wishList);
        ApiResponse apiResponse = new ApiResponse(true,"added to wishlist");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
