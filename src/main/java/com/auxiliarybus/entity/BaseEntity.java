package com.auxiliarybus.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangchaohui on 2019/1/25
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -606113837919897529L;
    public Date createdAt;

    public Date updatedAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
