package com.noriental.module.sms.service;


import com.noriental.module.sms.model.CommonDes;
import com.noriental.module.sms.model.RequestChuangCacheMessage;

/**
 * @date 2017/9/6
 */
public interface ChuangCacheMessageService {

    /**
     * http://wiki.okjiaoyu.cn/pages/viewpage.action?pageId=21549749#id-创世云短信接口定义-2.1发送短信
     * @param request
     * @return
     */
    CommonDes sendChuangCacheMessage(RequestChuangCacheMessage request);


}
