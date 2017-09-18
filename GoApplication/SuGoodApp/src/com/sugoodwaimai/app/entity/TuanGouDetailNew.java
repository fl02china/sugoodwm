package com.sugoodwaimai.app.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class TuanGouDetailNew implements Serializable {

    /**
     * Dianping : []
     * Shop : {"num":"0","shopName":"来福小吃店","tel":"8899111","score":"3","lng":"110.080935","Addr":"广东省雷州市","photo":"/attachs/2017/01/25/thumb_58885547cc324.jpg","lat":"20.925032"}
     * Tuan : {"timePeriod":"2017-11-01","others":null,"instructions":"<p>营业时间：8:00-22:00<\/p>","endDate":"2018-12-25","tuanPrice":150,"photo":"/attachs/2017/03/23/thumb_58d340ee8b83b.jpg","distribution":null,"warmPrompt":"温馨提示","intro":"仅售1.5元！价值2.0元鸡排","title":"仅售1.5元！价值2.0元鸡排","details":[{"comboId":1,"shopId":33,"tuanId":250,"comboName":"测试套餐","tuanDetails":[{"id":1,"comboId":1,"comnoName":"奥尔良鸡腿","number":"1对","price":4,"subtotal":4},{"id":2,"comboId":1,"comnoName":"薯条","number":"一份","price":5,"subtotal":5},{"id":3,"comboId":1,"comnoName":"特调果汁","number":"1呗","price":4,"subtotal":4}]},{"comboId":2,"shopId":33,"tuanId":250,"comboName":"测试套餐2","tuanDetails":[{"id":4,"comboId":2,"comnoName":"奥尔良鸡腿","number":"1对","price":4,"subtotal":4},{"id":5,"comboId":2,"comnoName":"奶茶","number":"1杯","price":5,"subtotal":5},{"id":6,"comboId":2,"comnoName":"布丁","number":"4个","price":4,"subtotal":4}]}],"makeReminding":"无须预约","tuanId":250,"Price":200,"validTime":"08:00-23:00","merchantServices":null,"thumb":null}
     */

    private ShopBean Shop;
    private TuanBean Tuan;
    private List<DianPing> Dianping;

    public ShopBean getShop() {
        return Shop;
    }

    public void setShop(ShopBean Shop) {
        this.Shop = Shop;
    }

    public TuanBean getTuan() {
        return Tuan;
    }

    public void setTuan(TuanBean Tuan) {
        this.Tuan = Tuan;
    }

    public List<DianPing> getDianping() {
        return Dianping;
    }

    public void setDianping(List<DianPing> Dianping) {
        this.Dianping = Dianping;
    }

    public static class ShopBean {
        /**
         * num : 0
         * shopName : 来福小吃店
         * tel : 8899111
         * score : 3
         * lng : 110.080935
         * Addr : 广东省雷州市
         * photo : /attachs/2017/01/25/thumb_58885547cc324.jpg
         * lat : 20.925032
         */

        private String num;
        private String shopName;
        private String tel;
        private String score;
        private String lng;
        private String Addr;
        private String photo;
        private String lat;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getAddr() {
            return Addr;
        }

        public void setAddr(String Addr) {
            this.Addr = Addr;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
    }

    public static class TuanBean {
        /**
         * timePeriod : 2017-11-01
         * others : null
         * instructions : <p>营业时间：8:00-22:00</p>
         * endDate : 2018-12-25
         * tuanPrice : 150
         * photo : /attachs/2017/03/23/thumb_58d340ee8b83b.jpg
         * distribution : null
         * warmPrompt : 温馨提示
         * intro : 仅售1.5元！价值2.0元鸡排
         * title : 仅售1.5元！价值2.0元鸡排
         * details : [{"comboId":1,"shopId":33,"tuanId":250,"comboName":"测试套餐","tuanDetails":[{"id":1,"comboId":1,"comnoName":"奥尔良鸡腿","number":"1对","price":4,"subtotal":4},{"id":2,"comboId":1,"comnoName":"薯条","number":"一份","price":5,"subtotal":5},{"id":3,"comboId":1,"comnoName":"特调果汁","number":"1呗","price":4,"subtotal":4}]},{"comboId":2,"shopId":33,"tuanId":250,"comboName":"测试套餐2","tuanDetails":[{"id":4,"comboId":2,"comnoName":"奥尔良鸡腿","number":"1对","price":4,"subtotal":4},{"id":5,"comboId":2,"comnoName":"奶茶","number":"1杯","price":5,"subtotal":5},{"id":6,"comboId":2,"comnoName":"布丁","number":"4个","price":4,"subtotal":4}]}]
         * makeReminding : 无须预约
         * tuanId : 250
         * Price : 200
         * validTime : 08:00-23:00
         * merchantServices : null
         * thumb : null
         */

        private String timePeriod;
        private Object others;
        private String instructions;
        private String endDate;
        private String tuanPrice;
        private String photo;
        private Object distribution;
        private String warmPrompt;
        private String intro;
        private String title;
        private String makeReminding;
        private String tuanId;
        private String Price;
        private String validTime;
        private Object merchantServices;
        private Object thumb;
        private List<DetailsBean> details;

        public String getTimePeriod() {
            return timePeriod;
        }

        public void setTimePeriod(String timePeriod) {
            this.timePeriod = timePeriod;
        }

        public Object getOthers() {
            return others;
        }

        public void setOthers(Object others) {
            this.others = others;
        }

        public String getInstructions() {
            return instructions;
        }

        public void setInstructions(String instructions) {
            this.instructions = instructions;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getTuanPrice() {
            return tuanPrice;
        }

        public void setTuanPrice(String tuanPrice) {
            this.tuanPrice = Double.parseDouble(tuanPrice) / 100 + "";
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public Object getDistribution() {
            return distribution;
        }

        public void setDistribution(Object distribution) {
            this.distribution = distribution;
        }

        public String getWarmPrompt() {
            return warmPrompt;
        }

        public void setWarmPrompt(String warmPrompt) {
            this.warmPrompt = warmPrompt;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMakeReminding() {
            return makeReminding;
        }

        public void setMakeReminding(String makeReminding) {
            this.makeReminding = makeReminding;
        }

        public String getTuanId() {
            return tuanId;
        }

        public void setTuanId(String tuanId) {
            this.tuanId = tuanId;
        }

        public String getPrice() {
            return Double.parseDouble(Price) / 100 + "";
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getValidTime() {
            return validTime;
        }

        public void setValidTime(String validTime) {
            this.validTime = validTime;
        }

        public Object getMerchantServices() {
            return merchantServices;
        }

        public void setMerchantServices(Object merchantServices) {
            this.merchantServices = merchantServices;
        }

        public Object getThumb() {
            return thumb;
        }

        public void setThumb(Object thumb) {
            this.thumb = thumb;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public static class DetailsBean {
            /**
             * comboId : 1
             * shopId : 33
             * tuanId : 250
             * comboName : 测试套餐
             * tuanDetails : [{"id":1,"comboId":1,"comnoName":"奥尔良鸡腿","number":"1对","price":4,"subtotal":4},{"id":2,"comboId":1,"comnoName":"薯条","number":"一份","price":5,"subtotal":5},{"id":3,"comboId":1,"comnoName":"特调果汁","number":"1呗","price":4,"subtotal":4}]
             */

            private int comboId;
            private int shopId;
            private int tuanId;
            private String comboName;
            private List<TuanDetailsBean> tuanDetails;

            public int getComboId() {
                return comboId;
            }

            public void setComboId(int comboId) {
                this.comboId = comboId;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public int getTuanId() {
                return tuanId;
            }

            public void setTuanId(int tuanId) {
                this.tuanId = tuanId;
            }

            public String getComboName() {
                return comboName;
            }

            public void setComboName(String comboName) {
                this.comboName = comboName;
            }

            public List<TuanDetailsBean> getTuanDetails() {
                return tuanDetails;
            }

            public void setTuanDetails(List<TuanDetailsBean> tuanDetails) {
                this.tuanDetails = tuanDetails;
            }

            @Override
            public String toString() {
                return "DetailsBean{" +
                        "comboId=" + comboId +
                        ", shopId=" + shopId +
                        ", tuanId=" + tuanId +
                        ", comboName='" + comboName + '\'' +
                        ", tuanDetails=" + tuanDetails +
                        '}';
            }

            public static class TuanDetailsBean {
                /**
                 * id : 1
                 * comboId : 1
                 * comnoName : 奥尔良鸡腿
                 * number : 1对
                 * price : 4
                 * subtotal : 4
                 */

                private int id;
                private int comboId;
                private String comnoName;
                private String number;
                private String price;
                private int subtotal;

                @Override
                public String toString() {
                    return "TuanDetailsBean{" +
                            "id=" + id +
                            ", comboId=" + comboId +
                            ", comnoName='" + comnoName + '\'' +
                            ", number='" + number + '\'' +
                            ", price=" + price +
                            ", subtotal=" + subtotal +
                            '}';
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getComboId() {
                    return comboId;
                }

                public void setComboId(int comboId) {
                    this.comboId = comboId;
                }

                public String getComnoName() {
                    return comnoName;
                }

                public void setComnoName(String comnoName) {
                    this.comnoName = comnoName;
                }

                public String getNumber() {
                    return number;
                }

                public void setNumber(String number) {
                    this.number = number;
                }

                public String getPrice() {
                    return Double.parseDouble(price) / 100 + "";
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public int getSubtotal() {
                    return subtotal;
                }

                public void setSubtotal(int subtotal) {
                    this.subtotal = subtotal;
                }
            }
        }
    }
}
