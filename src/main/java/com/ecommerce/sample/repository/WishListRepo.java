package com.ecommerce.sample.repository;

import com.ecommerce.sample.model.User;
import com.ecommerce.sample.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepo extends JpaRepository<WishList, Integer> {

    List<WishList> findAllByUser(User user);

}
