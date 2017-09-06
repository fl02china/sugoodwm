
package com.sugoodwaimai.app.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class TakeawayShopInfo implements Serializable {

    private static final long serialVersionUID = 1221738422267540168L;


    /**
     * session : true
     * Ele : [{"fanMoney":0,"isPay":0,"isNew":0,"shopName":"酸奶世家","shopId":30,"sinceMoney":1000,"jianDuos":0,"photo":"/attachs/2017/01/16/thumb_587c6d205ec02.jpg","isFan":0,"logistics":0,"fullMoney":0,"intro":"酸奶世家","newMoney":0}]
     * type : [{"小吃系列":[{"productId":390,"productName":"鸡米花","desc":"鸡米花","photo":"/attachs/2017/01/19/thumb_588021a3482a7.jpg","price":1200,"soldNum":0},{"productId":391,"productName":"包心豆腐","desc":"包心豆腐","photo":"/attachs/2017/01/19/thumb_588021c265579.jpg","price":1200,"soldNum":0},{"productId":393,"productName":"鸡中翅","desc":"鸡中翅","photo":"/attachs/2017/01/19/thumb_588021e856076.jpg","price":1700,"soldNum":0},{"productId":398,"productName":"绿茶芋卷","desc":"绿茶芋卷","photo":"/attachs/2017/01/19/thumb_588022201e10f.jpg","price":1400,"soldNum":0},{"productId":401,"productName":"奶香馒头","desc":"奶香馒头","photo":"/attachs/2017/01/19/thumb_5880225ccdc1f.jpg","price":1200,"soldNum":0},{"productId":404,"productName":"凤尾虾","desc":"凤尾虾","photo":"/attachs/2017/01/19/thumb_5880227d14160.jpg","price":1700,"soldNum":0},{"productId":406,"productName":"虾味卷","desc":"虾味卷","photo":"/attachs/2017/01/19/thumb_5880229e84f38.jpg","price":1400,"soldNum":0},{"productId":409,"productName":"南瓜饼","desc":"南瓜饼","photo":"/attachs/2017/01/19/thumb_588022c2c3c60.jpg","price":1200,"soldNum":0},{"productId":412,"productName":"黄金鱼丸","desc":"黄金鱼丸","photo":"/attachs/2017/01/19/thumb_588022e5db449.jpg","price":1200,"soldNum":0},{"productId":413,"productName":"薯条","desc":"薯条","photo":"/attachs/2017/01/19/thumb_58802302bed1d.jpg","price":1400,"soldNum":0},{"productId":414,"productName":"香芋地瓜丸","desc":"香芋地瓜丸","photo":"/attachs/2017/01/19/thumb_5880233b6864c.jpg","price":1500,"soldNum":0},{"productId":448,"productName":"绿茶佛饼","desc":"绿茶佛饼","photo":"/attachs/2017/05/15/thumb_59195d8ea1dce.jpg","price":1400,"soldNum":0},{"productId":455,"productName":"牛肉丸","desc":"牛肉丸","photo":"/attachs/2017/01/19/thumb_588028324d7cc.jpg","price":1400,"soldNum":0},{"productId":460,"productName":"天湖燕饺","desc":"天湖燕饺","photo":"/attachs/2017/01/19/thumb_588028e6ce50e.jpg","price":1400,"soldNum":0},{"productId":464,"productName":"双皮奶","desc":"双皮奶","photo":"/attachs/2017/05/15/thumb_59195c0855a42.jpg","price":1500,"soldNum":0},{"productId":467,"productName":"无双剑翅","desc":"无双剑翅","photo":"/attachs/2017/05/15/thumb_59195c9d548d9.jpg","price":1000,"soldNum":0},{"productId":472,"productName":"小香鸡柳","desc":"小香鸡柳","photo":"/attachs/2017/01/19/thumb_588029c78d0ec.jpg","price":1500,"soldNum":0},{"productId":478,"productName":"脆香藕合","desc":"脆香藕合","photo":"/attachs/2017/01/19/thumb_58802aaa82505.jpg","price":1500,"soldNum":0},{"productId":479,"productName":"鸡肉洋葱圈","desc":"鸡肉洋葱圈","photo":"/attachs/2017/01/19/thumb_58802b02aea32.jpg","price":1500,"soldNum":0},{"productId":480,"productName":"凉拌翅尖","desc":"凉拌翅尖","photo":"/attachs/2017/01/19/thumb_58802c2c097af.jpg","price":1500,"soldNum":0},{"productId":482,"productName":"米布丁酥","desc":"米布丁酥","photo":"/attachs/2017/01/19/thumb_58802d5bca195.JPG","price":1500,"soldNum":0},{"productId":508,"productName":"水果茶","desc":"水果茶","photo":"/attachs/2017/05/15/thumb_59195c6e60d78.jpg","price":1400,"soldNum":0},{"productId":3335,"productName":"蜜汁鸡爪","desc":"一份","photo":"/attachs/2017/05/15/thumb_59195a62eb930.jpg","price":1500,"soldNum":0},{"productId":3336,"productName":"川香鸡柳","desc":"一份","photo":"/attachs/2017/05/15/thumb_59195b9905cb3.jpg","price":1500,"soldNum":0},{"productId":3337,"productName":"太湖燕饺","desc":"一份","photo":"/attachs/2017/05/15/thumb_59195d32ed87e.jpg","price":1400,"soldNum":0},{"productId":3338,"productName":"胡萝卜字母鸡块","desc":"一份","photo":"/attachs/2017/05/15/thumb_59195e12b8fef.jpg","price":1500,"soldNum":0}],"盆栽酸奶":[{"productId":3375,"productName":"奥利奥盆栽酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_591999a160bc4.jpg","price":1000,"soldNum":0}],"爱上茶系列":[{"productId":509,"productName":"柠檬冰绿","desc":"柠檬冰绿","photo":"/attachs/2017/01/19/thumb_5880364ca3914.jpg","price":1100,"soldNum":0},{"productId":510,"productName":"柚柚红茶","desc":"柚柚红茶","photo":"/attachs/2017/05/15/thumb_59195eb25d62b.jpg","price":1100,"soldNum":0},{"productId":511,"productName":"蓝莓酿红茶","desc":"蓝莓酿红茶","photo":"/attachs/2017/05/15/thumb_59195edab90d1.jpg","price":1100,"soldNum":0},{"productId":3339,"productName":"柚柚茉香绿茶","desc":"一杯","photo":"/attachs/2017/05/15/thumb_59195f06abfdd.jpg","price":1100,"soldNum":0},{"productId":3340,"productName":"蜜桃茉香绿茶","desc":"一份","photo":"/attachs/2017/05/15/thumb_59195f456bc1f.jpg","price":1100,"soldNum":0}],"沙冰系列":[{"productId":3349,"productName":"香橙沙冰","desc":"一份","photo":"/attachs/2017/05/15/thumb_59196fd4b8882.jpg","price":1400,"soldNum":0},{"productId":3350,"productName":"百香果沙冰","desc":"一份","photo":"/attachs/2017/05/15/thumb_591971469e274.jpg","price":1400,"soldNum":0},{"productId":3351,"productName":"凤梨沙冰","desc":"一份","photo":"/attachs/2017/05/15/thumb_5919728dc00fa.jpg","price":1400,"soldNum":0},{"productId":3352,"productName":"蜂蜜玫瑰沙冰","desc":"一份","photo":"/attachs/2017/05/15/thumb_591972c08a500.jpg","price":1400,"soldNum":0},{"productId":3353,"productName":"锡兰奶茶沙冰","desc":"一份","photo":"/attachs/2017/05/15/thumb_591972e44b7c3.jpg","price":1400,"soldNum":0},{"productId":3354,"productName":"提拉米苏咖啡沙冰","desc":"一份","photo":"/attachs/2017/05/15/thumb_5919734766c60.jpg","price":1400,"soldNum":0}],"港式甜品":[{"productId":526,"productName":"鲜果双皮奶","desc":"鲜果双皮奶","photo":"/attachs/2017/01/19/thumb_588065a54c6d4.jpg","price":1100,"soldNum":0},{"productId":3380,"productName":"丝滑布丁","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199be12451b.jpg","price":800,"soldNum":0},{"productId":3381,"productName":"红豆双皮奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199c5c80ca6.jpg","price":1000,"soldNum":0},{"productId":3383,"productName":"干果双皮奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199ca664d3d.jpg","price":1200,"soldNum":0}],"现酿老酸奶":[{"productId":3356,"productName":"蓝莓酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_591974b4594c1.jpg","price":1000,"soldNum":0},{"productId":3357,"productName":"草莓酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_591974f82129b.jpg","price":1000,"soldNum":0},{"productId":3358,"productName":"蜂蜜酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_591975210a0f8.jpg","price":1000,"soldNum":0},{"productId":3359,"productName":"玫瑰酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_591975f729b00.jpg","price":1000,"soldNum":0},{"productId":3360,"productName":"经典原味酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_59197634f1b62.jpg","price":800,"soldNum":0},{"productId":3361,"productName":"红枣养颜酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_59197666e1327.jpg","price":800,"soldNum":0},{"productId":3362,"productName":"绿茶青春瘦身酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_591976a84ab10.jpg","price":1000,"soldNum":0},{"productId":3363,"productName":"木瓜香蕉美白酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_591976d78c2b4.jpg","price":1000,"soldNum":0},{"productId":3364,"productName":"火龙果炫色酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_591977d80e498.jpg","price":1000,"soldNum":0},{"productId":3365,"productName":"香甜哈密瓜酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_591978ae49649.jpg","price":1000,"soldNum":0},{"productId":3366,"productName":"最爱芒果酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_591978f1d8745.jpg","price":1000,"soldNum":0},{"productId":3367,"productName":"苹果雪梨酷味酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_591979687a078.jpg","price":1000,"soldNum":0},{"productId":3368,"productName":"缤纷青提酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_591979a86d06f.jpg","price":1000,"soldNum":0},{"productId":3369,"productName":"奇珍异果酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_59197a5598595.jpg","price":1000,"soldNum":0},{"productId":3370,"productName":"营养坚果酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_59197a8605e40.jpg","price":1200,"soldNum":0},{"productId":3371,"productName":"黄桃芒果酸奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_59197b27cd3c5.jpg","price":1000,"soldNum":0},{"productId":4664,"productName":"红酒酸奶","desc":"一份","photo":"/attachs/2017/06/15/thumb_5942024553af5.jpg","price":1000,"soldNum":0}],"酸奶水果捞系列":[{"productId":973,"productName":"水果拼盘","desc":"水果拼盘","photo":"/attachs/2017/05/15/thumb_59197b76ad3fa.jpg","price":2000,"soldNum":0},{"productId":3372,"productName":"多彩鲜活水果水果捞（小份）","desc":"一份","photo":"/attachs/2017/05/15/thumb_591995b8915ee.jpg","price":1400,"soldNum":0},{"productId":3373,"productName":"多彩鲜活水果水果捞（大份）","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199909444e2.jpg","price":2200,"soldNum":0},{"productId":3374,"productName":"招牌酸奶水果沙拉","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199953dff1c.jpg","price":2200,"soldNum":0}],"奶茶系列":[{"productId":521,"productName":"红豆奶茶","desc":"红豆奶茶","photo":"/attachs/2017/05/15/thumb_5919601be5efd.jpg","price":1200,"soldNum":0},{"productId":522,"productName":"布丁奶茶","desc":"布丁奶茶","photo":"/attachs/2017/05/15/thumb_5919603cddfc8.jpg","price":1200,"soldNum":0},{"productId":523,"productName":"爆爆奶茶","desc":"爆爆奶茶","photo":"/attachs/2017/05/15/thumb_59196082b1398.jpg","price":1200,"soldNum":0},{"productId":524,"productName":"茉香奶茶","desc":"茉香奶茶","photo":"/attachs/2017/05/15/thumb_59195ffdd22cc.jpg","price":900,"soldNum":0},{"productId":525,"productName":"阿萨姆奶茶","desc":"阿萨姆奶茶","photo":"/attachs/2017/05/15/thumb_59195fb0660c6.jpg","price":900,"soldNum":0}],"益生菌果奶系列":[{"productId":516,"productName":"香蕉牛奶","desc":"香蕉牛奶","photo":"/attachs/2017/05/15/thumb_59196c1845ac4.jpg","price":1400,"soldNum":0},{"productId":3343,"productName":"蓝莓酸酸乳","desc":"一瓶","photo":"/attachs/2017/05/15/thumb_5919684dc8cec.jpg","price":1400,"soldNum":0},{"productId":3344,"productName":"草莓QQ奶","desc":"一杯","photo":"/attachs/2017/05/15/thumb_591968afd0f7c.jpg","price":1400,"soldNum":0},{"productId":3345,"productName":"香芒木瓜益菌多","desc":"一份","photo":"/attachs/2017/05/15/thumb_591969153f2cf.jpg","price":1400,"soldNum":0},{"productId":3346,"productName":"莓丽果纤","desc":"一份","photo":"/attachs/2017/05/15/thumb_59196a570ec4f.jpg","price":1400,"soldNum":0},{"productId":3347,"productName":"水蜜桃牛奶","desc":"一杯","photo":"/attachs/2017/05/15/thumb_59196ce88890d.jpg","price":1400,"soldNum":0},{"productId":3348,"productName":"黑加仑牛奶","desc":"一份","photo":"/attachs/2017/05/15/thumb_59196d11b9c30.jpg","price":1400,"soldNum":0}],"酸奶奶昔系列":[{"productId":3384,"productName":"芒果奶昔","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199de587d01.jpg","price":1100,"soldNum":0},{"productId":3385,"productName":"草莓YOGURT奶昔","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199e9337cb1.jpg","price":1100,"soldNum":0},{"productId":3386,"productName":"香草YOGURT奶昔","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199ebbdcf19.jpg","price":1100,"soldNum":0},{"productId":3387,"productName":"抹茶YOGURT奶昔","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199ed8c09ff.jpg","price":1100,"soldNum":0},{"productId":3388,"productName":"巧克力YOGURT奶昔","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199efda5903.jpg","price":1100,"soldNum":0},{"productId":3389,"productName":"摩卡奶昔","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199f343ef8b.jpg","price":1100,"soldNum":0},{"productId":3390,"productName":"蓝莓芝士奶昔","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199f666bd00.jpg","price":1100,"soldNum":0}],"鲜榨果汁":[{"productId":428,"productName":"鲜爽西瓜汁","desc":"鲜爽西瓜汁","photo":"/attachs/2017/01/19/thumb_588024ef542bf.jpg","price":1400,"soldNum":0},{"productId":513,"productName":"柠檬水","desc":"柠檬水","photo":"/attachs/2017/05/15/thumb_5919655b9eb67.jpg","price":1000,"soldNum":0},{"productId":514,"productName":"金桔柠檬汁","desc":"金桔柠檬汁","photo":"/attachs/2017/05/15/thumb_591965e94e776.jpg","price":1200,"soldNum":0},{"productId":515,"productName":"百香果汁","desc":"百香果汁","photo":"/attachs/2017/01/19/thumb_588039c7f1634.jpg","price":1200,"soldNum":0},{"productId":517,"productName":"百益木瓜汁","desc":"百益木瓜汁","photo":"/attachs/2017/05/15/thumb_591966d84649e.jpg","price":1400,"soldNum":0},{"productId":518,"productName":"鲜榨哈密瓜汁","desc":"鲜榨哈密瓜汁","photo":"/attachs/2017/05/15/thumb_5919672142487.jpg","price":1400,"soldNum":0},{"productId":519,"productName":"新鲜芒果汁","desc":"美容功效:皮肤嫩白红润，去皱清斑","photo":"/attachs/2017/05/15/thumb_591960ec2586c.jpg","price":1400,"soldNum":0},{"productId":3341,"productName":"香蕉牛奶汁","desc":"一杯","photo":"/attachs/2017/05/15/thumb_5919667f66ef8.jpg","price":1200,"soldNum":0},{"productId":3342,"productName":"奇异果果汁","desc":"一杯","photo":"/attachs/2017/05/15/thumb_591967676e039.jpg","price":1400,"soldNum":0}],"手摇酸奶系列":[{"productId":3379,"productName":"手摇酸奶大杯","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199b7d4ad37.jpg","price":1200,"soldNum":0},{"productId":3376,"productName":"芒果味","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199aa679cd2.jpg","price":800,"soldNum":0},{"productId":3377,"productName":"双莓味","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199ace0e6ac.jpg","price":800,"soldNum":0},{"productId":3378,"productName":"奇异果味","desc":"一份","photo":"/attachs/2017/05/15/thumb_59199afa900da.jpg","price":800,"soldNum":0}],"水果酸奶系列":[{"productId":3391,"productName":"蓝柑蜜柚","desc":"一份","photo":"/attachs/2017/05/15/thumb_5919a281be989.jpg","price":1200,"soldNum":0},{"productId":3392,"productName":"薄荷青柠","desc":"一份","photo":"/attachs/2017/05/15/thumb_5919a2e3c7b75.jpg","price":1200,"soldNum":0},{"productId":3393,"productName":"草莓青柠","desc":"一份","photo":"/attachs/2017/05/15/thumb_5919a31c076e5.jpg","price":1200,"soldNum":0},{"productId":3394,"productName":"森林浆果","desc":"一份","photo":"/attachs/2017/05/15/thumb_5919a34f399bf.jpg","price":1200,"soldNum":0},{"productId":3395,"productName":"柠檬C-100","desc":"一份","photo":"/attachs/2017/05/15/thumb_5919a3725e54a.jpg","price":1200,"soldNum":0},{"productId":3396,"productName":"香槟葡萄","desc":"一份","photo":"/attachs/2017/05/15/thumb_5919a3be84b01.jpg","price":1200,"soldNum":0}]}]
     */

    private boolean session;
    private List<EleBean> Ele;
    private List<TypeBean> type;

    public boolean isSession() {
        return session;
    }

    public void setSession(boolean session) {
        this.session = session;
    }

    public List<EleBean> getEle() {
        return Ele;
    }

    public void setEle(List<EleBean> Ele) {
        this.Ele = Ele;
    }

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
    }

    public static class EleBean implements Serializable {
        /**
         * fanMoney : 0
         * isPay : 0
         * isNew : 0
         * shopName : 酸奶世家
         * shopId : 30
         * sinceMoney : 1000
         * jianDuos : 0
         * photo : /attachs/2017/01/16/thumb_587c6d205ec02.jpg
         * isFan : 0
         * logistics : 0
         * fullMoney : 0
         * intro : 酸奶世家
         * newMoney : 0
         */

        private int fanMoney;
        private int isPay;
        private int isNew;
        private String shopName;
        private int shopId;
        private int sinceMoney;
        private int jianDuos;
        private String photo;
        private int isFan;
        private Double logistics;
        private int fullMoney;
        private String intro;
        private int newMoney;
        private int score;
        private String addr;
        private String mobile;
        private String tel;
        private String busihour;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getBusihour() {
            return busihour;
        }

        public void setBusihour(String busihour) {
            this.busihour = busihour;
        }

        public int getFanMoney() {
            return fanMoney;
        }

        public void setFanMoney(int fanMoney) {
            this.fanMoney = fanMoney;
        }

        public int getIsPay() {
            return isPay;
        }

        public void setIsPay(int isPay) {
            this.isPay = isPay;
        }

        public int getIsNew() {
            return isNew;
        }

        public void setIsNew(int isNew) {
            this.isNew = isNew;
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

        public int getSinceMoney() {
            return sinceMoney/100;
        }

        public void setSinceMoney(int sinceMoney) {
            this.sinceMoney = sinceMoney;
        }

        public int getJianDuos() {
            return jianDuos;
        }

        public void setJianDuos(int jianDuos) {
            this.jianDuos = jianDuos;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getIsFan() {
            return isFan;
        }

        public void setIsFan(int isFan) {
            this.isFan = isFan;
        }

        public Double getLogistics() {
            return logistics/100;
        }

        public void setLogistics(Double logistics) {
            this.logistics = logistics;
        }

        public int getFullMoney() {
            return fullMoney;
        }

        public void setFullMoney(int fullMoney) {
            this.fullMoney = fullMoney;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getNewMoney() {
            return newMoney;
        }

        public void setNewMoney(int newMoney) {
            this.newMoney = newMoney;
        }

        @Override
        public String toString() {
            return "EleBean{" +
                    "fanMoney=" + fanMoney +
                    ", isPay=" + isPay +
                    ", isNew=" + isNew +
                    ", shopName='" + shopName + '\'' +
                    ", shopId=" + shopId +
                    ", sinceMoney=" + sinceMoney +
                    ", jianDuos=" + jianDuos +
                    ", photo='" + photo + '\'' +
                    ", isFan=" + isFan +
                    ", logistics=" + logistics +
                    ", fullMoney=" + fullMoney +
                    ", intro='" + intro + '\'' +
                    ", newMoney=" + newMoney +
                    ", score=" + score +
                    ", addr='" + addr + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", tel='" + tel + '\'' +
                    ", busihour='" + busihour + '\'' +
                    '}';
        }
    }

    public static class TypeBean {
        private List<小吃系列Bean> 小吃系列;
        private List<盆栽酸奶Bean> 盆栽酸奶;
        private List<爱上茶系列Bean> 爱上茶系列;
        private List<沙冰系列Bean> 沙冰系列;
        private List<港式甜品Bean> 港式甜品;
        private List<现酿老酸奶Bean> 现酿老酸奶;
        private List<酸奶水果捞系列Bean> 酸奶水果捞系列;
        private List<奶茶系列Bean> 奶茶系列;
        private List<益生菌果奶系列Bean> 益生菌果奶系列;
        private List<酸奶奶昔系列Bean> 酸奶奶昔系列;
        private List<鲜榨果汁Bean> 鲜榨果汁;
        private List<手摇酸奶系列Bean> 手摇酸奶系列;
        private List<水果酸奶系列Bean> 水果酸奶系列;

        public List<小吃系列Bean> get小吃系列() {
            return 小吃系列;
        }

        public void set小吃系列(List<小吃系列Bean> 小吃系列) {
            this.小吃系列 = 小吃系列;
        }

        public List<盆栽酸奶Bean> get盆栽酸奶() {
            return 盆栽酸奶;
        }

        public void set盆栽酸奶(List<盆栽酸奶Bean> 盆栽酸奶) {
            this.盆栽酸奶 = 盆栽酸奶;
        }

        public List<爱上茶系列Bean> get爱上茶系列() {
            return 爱上茶系列;
        }

        public void set爱上茶系列(List<爱上茶系列Bean> 爱上茶系列) {
            this.爱上茶系列 = 爱上茶系列;
        }

        public List<沙冰系列Bean> get沙冰系列() {
            return 沙冰系列;
        }

        public void set沙冰系列(List<沙冰系列Bean> 沙冰系列) {
            this.沙冰系列 = 沙冰系列;
        }

        public List<港式甜品Bean> get港式甜品() {
            return 港式甜品;
        }

        public void set港式甜品(List<港式甜品Bean> 港式甜品) {
            this.港式甜品 = 港式甜品;
        }

        public List<现酿老酸奶Bean> get现酿老酸奶() {
            return 现酿老酸奶;
        }

        public void set现酿老酸奶(List<现酿老酸奶Bean> 现酿老酸奶) {
            this.现酿老酸奶 = 现酿老酸奶;
        }

        public List<酸奶水果捞系列Bean> get酸奶水果捞系列() {
            return 酸奶水果捞系列;
        }

        public void set酸奶水果捞系列(List<酸奶水果捞系列Bean> 酸奶水果捞系列) {
            this.酸奶水果捞系列 = 酸奶水果捞系列;
        }

        public List<奶茶系列Bean> get奶茶系列() {
            return 奶茶系列;
        }

        public void set奶茶系列(List<奶茶系列Bean> 奶茶系列) {
            this.奶茶系列 = 奶茶系列;
        }

        public List<益生菌果奶系列Bean> get益生菌果奶系列() {
            return 益生菌果奶系列;
        }

        public void set益生菌果奶系列(List<益生菌果奶系列Bean> 益生菌果奶系列) {
            this.益生菌果奶系列 = 益生菌果奶系列;
        }

        public List<酸奶奶昔系列Bean> get酸奶奶昔系列() {
            return 酸奶奶昔系列;
        }

        public void set酸奶奶昔系列(List<酸奶奶昔系列Bean> 酸奶奶昔系列) {
            this.酸奶奶昔系列 = 酸奶奶昔系列;
        }

        public List<鲜榨果汁Bean> get鲜榨果汁() {
            return 鲜榨果汁;
        }

        public void set鲜榨果汁(List<鲜榨果汁Bean> 鲜榨果汁) {
            this.鲜榨果汁 = 鲜榨果汁;
        }

        public List<手摇酸奶系列Bean> get手摇酸奶系列() {
            return 手摇酸奶系列;
        }

        public void set手摇酸奶系列(List<手摇酸奶系列Bean> 手摇酸奶系列) {
            this.手摇酸奶系列 = 手摇酸奶系列;
        }

        public List<水果酸奶系列Bean> get水果酸奶系列() {
            return 水果酸奶系列;
        }

        public void set水果酸奶系列(List<水果酸奶系列Bean> 水果酸奶系列) {
            this.水果酸奶系列 = 水果酸奶系列;
        }

        public static class 小吃系列Bean {
            /**
             * productId : 390
             * productName : 鸡米花
             * desc : 鸡米花
             * photo : /attachs/2017/01/19/thumb_588021a3482a7.jpg
             * price : 1200
             * soldNum : 0
             */

            private int productId;
            private String productName;
            private String desc;
            private String photo;
            private int price;
            private int soldNum;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSoldNum() {
                return soldNum;
            }

            public void setSoldNum(int soldNum) {
                this.soldNum = soldNum;
            }
        }

        public static class 盆栽酸奶Bean {
            /**
             * productId : 3375
             * productName : 奥利奥盆栽酸奶
             * desc : 一份
             * photo : /attachs/2017/05/15/thumb_591999a160bc4.jpg
             * price : 1000
             * soldNum : 0
             */

            private int productId;
            private String productName;
            private String desc;
            private String photo;
            private int price;
            private int soldNum;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSoldNum() {
                return soldNum;
            }

            public void setSoldNum(int soldNum) {
                this.soldNum = soldNum;
            }
        }

        public static class 爱上茶系列Bean {
            /**
             * productId : 509
             * productName : 柠檬冰绿
             * desc : 柠檬冰绿
             * photo : /attachs/2017/01/19/thumb_5880364ca3914.jpg
             * price : 1100
             * soldNum : 0
             */

            private int productId;
            private String productName;
            private String desc;
            private String photo;
            private int price;
            private int soldNum;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSoldNum() {
                return soldNum;
            }

            public void setSoldNum(int soldNum) {
                this.soldNum = soldNum;
            }
        }

        public static class 沙冰系列Bean {
            /**
             * productId : 3349
             * productName : 香橙沙冰
             * desc : 一份
             * photo : /attachs/2017/05/15/thumb_59196fd4b8882.jpg
             * price : 1400
             * soldNum : 0
             */

            private int productId;
            private String productName;
            private String desc;
            private String photo;
            private int price;
            private int soldNum;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSoldNum() {
                return soldNum;
            }

            public void setSoldNum(int soldNum) {
                this.soldNum = soldNum;
            }
        }

        public static class 港式甜品Bean {
            /**
             * productId : 526
             * productName : 鲜果双皮奶
             * desc : 鲜果双皮奶
             * photo : /attachs/2017/01/19/thumb_588065a54c6d4.jpg
             * price : 1100
             * soldNum : 0
             */

            private int productId;
            private String productName;
            private String desc;
            private String photo;
            private int price;
            private int soldNum;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSoldNum() {
                return soldNum;
            }

            public void setSoldNum(int soldNum) {
                this.soldNum = soldNum;
            }
        }

        public static class 现酿老酸奶Bean {
            /**
             * productId : 3356
             * productName : 蓝莓酸奶
             * desc : 一份
             * photo : /attachs/2017/05/15/thumb_591974b4594c1.jpg
             * price : 1000
             * soldNum : 0
             */

            private int productId;
            private String productName;
            private String desc;
            private String photo;
            private int price;
            private int soldNum;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSoldNum() {
                return soldNum;
            }

            public void setSoldNum(int soldNum) {
                this.soldNum = soldNum;
            }
        }

        public static class 酸奶水果捞系列Bean {
            /**
             * productId : 973
             * productName : 水果拼盘
             * desc : 水果拼盘
             * photo : /attachs/2017/05/15/thumb_59197b76ad3fa.jpg
             * price : 2000
             * soldNum : 0
             */

            private int productId;
            private String productName;
            private String desc;
            private String photo;
            private int price;
            private int soldNum;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSoldNum() {
                return soldNum;
            }

            public void setSoldNum(int soldNum) {
                this.soldNum = soldNum;
            }
        }

        public static class 奶茶系列Bean {
            /**
             * productId : 521
             * productName : 红豆奶茶
             * desc : 红豆奶茶
             * photo : /attachs/2017/05/15/thumb_5919601be5efd.jpg
             * price : 1200
             * soldNum : 0
             */

            private int productId;
            private String productName;
            private String desc;
            private String photo;
            private int price;
            private int soldNum;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSoldNum() {
                return soldNum;
            }

            public void setSoldNum(int soldNum) {
                this.soldNum = soldNum;
            }
        }

        public static class 益生菌果奶系列Bean {
            /**
             * productId : 516
             * productName : 香蕉牛奶
             * desc : 香蕉牛奶
             * photo : /attachs/2017/05/15/thumb_59196c1845ac4.jpg
             * price : 1400
             * soldNum : 0
             */

            private int productId;
            private String productName;
            private String desc;
            private String photo;
            private int price;
            private int soldNum;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSoldNum() {
                return soldNum;
            }

            public void setSoldNum(int soldNum) {
                this.soldNum = soldNum;
            }
        }

        public static class 酸奶奶昔系列Bean {
            /**
             * productId : 3384
             * productName : 芒果奶昔
             * desc : 一份
             * photo : /attachs/2017/05/15/thumb_59199de587d01.jpg
             * price : 1100
             * soldNum : 0
             */

            private int productId;
            private String productName;
            private String desc;
            private String photo;
            private int price;
            private int soldNum;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSoldNum() {
                return soldNum;
            }

            public void setSoldNum(int soldNum) {
                this.soldNum = soldNum;
            }
        }

        public static class 鲜榨果汁Bean {
            /**
             * productId : 428
             * productName : 鲜爽西瓜汁
             * desc : 鲜爽西瓜汁
             * photo : /attachs/2017/01/19/thumb_588024ef542bf.jpg
             * price : 1400
             * soldNum : 0
             */

            private int productId;
            private String productName;
            private String desc;
            private String photo;
            private int price;
            private int soldNum;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSoldNum() {
                return soldNum;
            }

            public void setSoldNum(int soldNum) {
                this.soldNum = soldNum;
            }
        }

        public static class 手摇酸奶系列Bean {
            /**
             * productId : 3379
             * productName : 手摇酸奶大杯
             * desc : 一份
             * photo : /attachs/2017/05/15/thumb_59199b7d4ad37.jpg
             * price : 1200
             * soldNum : 0
             */

            private int productId;
            private String productName;
            private String desc;
            private String photo;
            private int price;
            private int soldNum;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSoldNum() {
                return soldNum;
            }

            public void setSoldNum(int soldNum) {
                this.soldNum = soldNum;
            }
        }

        public static class 水果酸奶系列Bean {
            /**
             * productId : 3391
             * productName : 蓝柑蜜柚
             * desc : 一份
             * photo : /attachs/2017/05/15/thumb_5919a281be989.jpg
             * price : 1200
             * soldNum : 0
             */

            private int productId;
            private String productName;
            private String desc;
            private String photo;
            private int price;
            private int soldNum;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSoldNum() {
                return soldNum;
            }

            public void setSoldNum(int soldNum) {
                this.soldNum = soldNum;
            }
        }
    }
}