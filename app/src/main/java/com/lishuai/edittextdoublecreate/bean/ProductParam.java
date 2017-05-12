package com.lishuai.edittextdoublecreate.bean;

import java.io.Serializable;

/**
 * @author 李帅
 * @time 2017/5/13  1:03
 * @desc ${TODD}
 */

public class ProductParam implements Serializable {

    public ProductParam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public ProductParam( ) {
    }

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
