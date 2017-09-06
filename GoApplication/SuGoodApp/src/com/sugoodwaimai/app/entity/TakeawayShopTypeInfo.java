package com.sugoodwaimai.app.entity;

/**
 * Package :com.sugoodwaimai.app.entity
 * Description :
 * Author :Rc3
 * Created at :2017/3/31 09:48.
 */

public class TakeawayShopTypeInfo {
    String price;
    String desc;
    String productName;
    int soldNum;
    int productId;
    String photo;
    int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrice() {
        return Double.parseDouble(price + "") / 100 + "元";
    }

    public double getIntPrice() {
        return Double.parseDouble(price + "") / 100;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSoldNum() {
        return "月售 " + soldNum;
    }

    public void setSoldNum(int soldNum) {
        this.soldNum = soldNum;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}