package com.ecommerce.sample.dto;

import java.util.List;

public class CartDto {

    private Double totalcost;
    private List<CartitemDto> cartitrms;

    public CartDto() {
    }

    public List<CartitemDto> getCartitrms() {
        return cartitrms;
    }

    public void setCartitrms(List<CartitemDto> cartitrms) {
        this.cartitrms = cartitrms;
    }

    public Double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(Double totalcost) {
        this.totalcost = totalcost;
    }


}
