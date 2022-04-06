package com.ecommerce.sample.service;

import com.ecommerce.sample.dto.AddToCartDto;
import com.ecommerce.sample.dto.CartDto;
import com.ecommerce.sample.dto.CartitemDto;
import com.ecommerce.sample.dto.ProductDto;
import com.ecommerce.sample.model.Cart;
import com.ecommerce.sample.model.Product;
import com.ecommerce.sample.model.User;
import com.ecommerce.sample.model.WishList;
import com.ecommerce.sample.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private ProductService productService;

    @Autowired
    CartRepo cartRepo;

    public CartDto listcartitems(User user) {
        List<Cart> allCartProdByUser = cartRepo.findAllByUser(user);


        List<CartitemDto> cartitems = new ArrayList<>();
        double totalprice = 0;

        for (Cart cart : allCartProdByUser) {
            CartitemDto cartitemDto = new CartitemDto(cart);
            totalprice = totalprice + (cartitemDto.getQuantity() * cart.getProduct().getProoductPrice());
            cartitems.add(cartitemDto);
        }
        CartDto cartDto = new CartDto();
        cartDto.setTotalcost(totalprice);
        cartDto.setCartitrms(cartitems);
        return cartDto;
    }




    public void addToCart(AddToCartDto addToCartDto, User user) throws Exception {
        //check product id
        Product products = productService.findById(addToCartDto.getProductid());

        Cart cart = new Cart();
        cart.setProduct(products);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreateddate(new Date());

        //save cart
        cartRepo.save(cart);
    }

    public void deleteItem(int cartItemid, User user) throws Exception {

        Optional<Cart> optionalCart = cartRepo.findById(cartItemid);

        if (optionalCart.isEmpty()){
            throw new Exception("id invalid");
        }
        Cart cart = optionalCart.get();

        if (cart.getUser() != user){
            throw new Exception("id user");
        }

        cartRepo.delete(cart);
    }
}
