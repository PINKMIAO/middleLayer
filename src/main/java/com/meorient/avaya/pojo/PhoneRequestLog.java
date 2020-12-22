package com.meorient.avaya.pojo;

public class PhoneRequestLog {

    private int id;
    private String originPhone;
    private String reqTime;
    private String requestData;
    private String responseData;
    private String runTime;
    //
    private int success;

    public PhoneRequestLog() {
    }

    public PhoneRequestLog(int id, String originPhone, String reqTime, String requestData,
                           String responseData, String runTime, int success) {
        this.id = id;
        this.originPhone = originPhone;
        this.reqTime = reqTime;
        this.requestData = requestData;
        this.responseData = responseData;
        this.runTime = runTime;
        this.success = success;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOriginPhone(String originPhone) {
        this.originPhone = originPhone;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "PhoneAttriLog{" +
                "id=" + id +
                ", originPhone='" + originPhone + '\'' +
                ", reqTime='" + reqTime + '\'' +
                ", requestData='" + requestData + '\'' +
                ", responseData='" + responseData + '\'' +
                ", runTime='" + runTime + '\'' +
                ", success=" + success +
                '}';
    }
}
