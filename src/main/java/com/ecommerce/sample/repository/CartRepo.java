package com.ecommerce.sample.repository;

import com.ecommerce.sample.model.Cart;
import com.ecommerce.sample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {

    List<Cart> findAllByUser(User user);

}
