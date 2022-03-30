package com.ecommerce.sample.dto;

public class ProductDto {

    private Integer prooductid;

    private String prooductName;

    private String prooductDescription;

    private String imageUrl;

    private Double prooductPrice;

    private Integer Categoryid;

    public ProductDto() {
    }

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

    public Integer getCategoryid() {
        return Categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        Categoryid = categoryid;
    }

    public Integer getProoductid() {
        return prooductid;
    }

    public void setProoductid(Integer prooductid) {
        this.prooductid = prooductid;
    }
}
