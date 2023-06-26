package com.ohgiraffers.handlermethod;

public class MenuDTO {

    private String name;
    private int price;
    private int categortyCode;
    private String orderableStatus;

    public MenuDTO(){}

    public MenuDTO(String name, int price, int categortyCode, String orderableStatus) {
        this.name = name;
        this.price = price;
        this.categortyCode = categortyCode;
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categortyCode=" + categortyCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategortyCode(int categortyCode) {
        this.categortyCode = categortyCode;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCategortyCode() {
        return categortyCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }
}
