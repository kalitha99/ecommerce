package com.ecommerce.sample.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wishlist")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wliId;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    private User user;

    @Column(name = "createddate")
    private Date createddate;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    public WishList(User user, Product product) {
        this.user = user;
        this.product = product;
        this.createddate = new Date();
    }

    public WishList() {
    }



    public WishList(Integer wliId, User user, Date createddate, Product product) {
        this.wliId = wliId;
        this.user = user;
        this.createddate = createddate;
        this.product = product;
    }

    public Integer getWliId() {
        return wliId;
    }

    public void setWliId(Integer wliId) {
        this.wliId = wliId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
