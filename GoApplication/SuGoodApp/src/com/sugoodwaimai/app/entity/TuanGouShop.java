package com.sugoodwaimai.app.entity;

import java.io.Serializable;

/**
 * Package :com.android.supermarket.takeaway.bean
 * Description :
 * Author :Rc3
 * Created at :2017/2/28 10:47.
 */

public class TuanGouShop implements Serializable {


    private static final long serialVersionUID = 3104985075222248879L;

    private int min;
    private String logo;


    private int score;


    private String photo;


    private int soldNum;


    private String shopName;
    private int shopId;
    private  int price;



    @Override
    public String toString() {
        return "TakeawayShop{" +
                "min=" + min +
                ", logo='" + logo + '\'' +

                ", score=" + score +

                ", price='" + price + '\'' +
                ", photo='" + photo + '\'' +

                ", soldNum=" + soldNum +

                ", shopName='" + shopName + '\'' +
                ", shopId=" + shopId +

                '}';
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getSoldNum() {
        return soldNum;
    }

    public void setSoldNum(int soldNum) {
        this.soldNum = soldNum;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }
}
