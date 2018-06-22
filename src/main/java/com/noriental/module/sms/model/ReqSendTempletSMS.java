package com.noriental.module.sms.model;

import javax.validation.constraints.NotNull;

/**
 * Created by liuhuapeng on 2016/11/1.
 */
public class ReqSendTempletSMS  {
    private String mobile;
    private String templet;
    //@NotNull
    //private PipelineEnum pipelineEnum = PipelineEnum.DEFAULT;

    public String getTemplet() {
        return templet;
    }

    public void setTemplet(String templet) {
        this.templet = templet;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    /*public PipelineEnum getPipelineEnum() {
        return pipelineEnum;
    }
*/
   /* public void setPipelineEnum(PipelineEnum pipelineEnum) {
        this.pipelineEnum = pipelineEnum;
    }

    public void validata() {
        if (!StringUtils.hasText(mobile)) {
            throw new BizLayerException("mobile is empty", BasicErrorCode.BASIC_INPUT_PARAM_ERROR);
        }
        if (mobile.getBytes().length != 11) {
            throw new BizLayerException("mobile len!=11", BasicErrorCode.BASIC_INPUT_PARAM_ERROR);
        }
        if (!StringUtils.hasText(templet)) {
            throw new BizLayerException("templet is empty", BasicErrorCode.BASIC_INPUT_PARAM_ERROR);
        }
    }*/
}
