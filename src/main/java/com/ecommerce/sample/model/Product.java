package com.ecommerce.sample.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prooductid;


    private String prooductName;

    private String prooductDescription;

    private String imageUrl;

    private Double prooductPrice;


    //manmy to one
    @ManyToOne
    @JoinColumn(name = "categoryid")
    Category category;



    public String getProoductName() {
        return prooductName;
    }

    public void setProoductName(String prooductName) {
        this.prooductName = prooductName;
    }

    public String getProoductDescription() {
        return prooductDescription;
    }

    public void setProoductDescription(String prooductDescription) {
        this.prooductDescription = prooductDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getProoductPrice() {
        return prooductPrice;
    }

    public void setProoductPrice(Double prooductPrice) {
        this.prooductPrice = prooductPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getProoductid() {
        return prooductid;
    }

    public void setProoductid(Integer prooductid) {
        this.prooductid = prooductid;
    }
}
