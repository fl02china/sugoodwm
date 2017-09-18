package com.sugoodwaimai.app.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class TuanCode {


    /**
     * session : true
     * List : [{"code":"78359772","orderId":"201708031157379871","codeId":41,"tuanId":392},{"code":"46811983","orderId":"201708031157379871","codeId":42,"tuanId":392},{"code":"11761864","orderId":"201708031157379871","codeId":43,"tuanId":392}]
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
         * code : 78359772
         * orderId : 201708031157379871
         * codeId : 41
         * tuanId : 392
         */

        private String code;
        private String orderId;
        private int codeId;
        private int tuanId;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getCodeId() {
            return codeId;
        }

        public void setCodeId(int codeId) {
            this.codeId = codeId;
        }

        public int getTuanId() {
            return tuanId;
        }

        public void setTuanId(int tuanId) {
            this.tuanId = tuanId;
        }
    }
}
