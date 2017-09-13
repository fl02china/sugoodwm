package com.sugoodwaimai.app.activity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11 0011.
 */

public class TuanGouShop2 {

    /**
     * session : true
     * List : [{"shopId":94,"shopName":"测试店","logo":"/Speedpic/img/1504831821803.jpg","photo":"/attachs/2017/02/04/thumb_58954207cf2a7.jpg","score":3,"price":0,"min":0,"soldNum":563},{"shopId":45,"shopName":"莱茵阁西餐厅","logo":"/attachs/2017/01/17/thumb_587dc6bdaf46a.jpg","photo":"/attachs/2017/01/17/thumb_587dc65063e6e.jpg","score":3,"price":0,"min":130,"soldNum":20},{"shopId":137,"shopName":"恒恩轮胎店","logo":"/attachs/2017/02/14/thumb_58a268d015dbd.jpg","photo":"/attachs/2017/02/14/thumb_58a268ae3815d.jpg","score":3,"price":0,"min":172,"soldNum":66},{"shopId":38,"shopName":"禾福寿司","logo":"/attachs/2017/01/16/thumb_587c8eaba2186.JPG","photo":"/attachs/2017/01/16/thumb_587c8ea1ca5b2.JPG","score":3,"price":0,"min":229,"soldNum":300},{"shopId":59,"shopName":"转角随想吧","logo":"/attachs/2017/01/18/thumb_587f241fa8a69.JPG","photo":"/attachs/2017/01/18/thumb_587f23d0ea72a.JPG","score":3,"price":0,"min":258,"soldNum":154}]
     */

    private boolean session;
    private java.util.List<ListBean> List;

    public boolean isSession() {
        return session;
    }

    public void setSession(boolean session) {
        this.session = session;
    }

    public List<ListBean> getList() {
        return List;
    }

    public void setList(List<ListBean> List) {
        this.List = List;
    }

    public static class ListBean {
        /**
         * shopId : 94
         * shopName : 测试店
         * logo : /Speedpic/img/1504831821803.jpg
         * photo : /attachs/2017/02/04/thumb_58954207cf2a7.jpg
         * score : 3
         * price : 0
         * min : 0
         * soldNum : 563
         */

        private int shopId;
        private String shopName;
        private String logo;
        private String photo;
        private int score;
        private int price;
        private int min;
        private int soldNum;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
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

        public int getSoldNum() {
            return soldNum;
        }

        public void setSoldNum(int soldNum) {
            this.soldNum = soldNum;
        }
    }
}
