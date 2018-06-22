package com.noriental.module.sms.model;


import javax.validation.constraints.NotNull;

public class RequestChuangCacheMessage  {
    @NotNull
    private String mobile;
    @NotNull
    private String content;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
