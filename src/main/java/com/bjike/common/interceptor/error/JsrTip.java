package com.bjike.common.interceptor.error;

/**
 * Created by lake on 17-4-19.
 */
public class JsrTip {
    private String field;
    private String tip;
    public JsrTip(String field,String tip){
        this.field = field;
        this.tip = tip;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
