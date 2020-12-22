package com.meorient.avaya.pojo;

public class PhoneCache {

    private String originPhone;
    private String actualPhone;
    private String createTime;

    public PhoneCache() {
    }

    public PhoneCache(String originPhone, String actualPhone, String createTime) {
        this.originPhone = originPhone;
        this.actualPhone = actualPhone;
        this.createTime = createTime;
    }

    public void setOriginPhone(String originPhone) {
        this.originPhone = originPhone;
    }

    public void setActualPhone(String actualPhone) {
        this.actualPhone = actualPhone;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOriginPhone() {
        return originPhone;
    }

    public String getActualPhone() {
        return actualPhone;
    }

    public String getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "PhoneCache{" +
                "originPhone='" + originPhone + '\'' +
                ", actualPhone='" + actualPhone + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
