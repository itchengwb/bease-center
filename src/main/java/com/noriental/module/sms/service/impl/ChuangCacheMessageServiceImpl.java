package com.noriental.module.sms.service.impl;


import com.noriental.module.sms.dao.SmsSendMapper;
import com.noriental.module.sms.model.CommonDes;
import com.noriental.module.sms.model.ReqSendTempletSMS;
import com.noriental.module.sms.model.RequestChuangCacheMessage;
import com.noriental.module.sms.model.SmsSend;
import com.noriental.module.sms.service.ChuangCacheMessageService;
import com.noriental.util.JsonUtil;
import com.noriental.util.PipelineEnum;
import com.noriental.util.SendMailUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.invoke.MethodHandles;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 * @author dongyl
 */
@Service
public class ChuangCacheMessageServiceImpl implements ChuangCacheMessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final int SEND_MESSAGE_SUCCESS = 1000;
    /**
     * 开关生效
     */
    private static final String SWITCH_FLAG = "1";

    //@Value("${switch_mail}")
    public String switchMail = "1";

    //@Value("${switch_chuang}")
    public String switchChuang = "1";

    //@Value("${switch_mobile}")
    public String switchMobile = "1";

    //@Value("${app_key}")
    public String appKey ="485fa83a5fad0f4385b3835d9350036e";
    //@Value("${token_url}")
    public String tokenUrl = "https://api.chuangcache.com/OAuth/authorize";
    //@Value("${app_id}")
    public String appId = "mRHHHc9LlNgzLVDX";
    //@Value("${app_secret}")
    public String appSecret ="ocQ3kRA1YZAEFNBnvPWl68lLFeGaak08";
    //@Value("${send_msg_url}")
    public String sendMSGUrl ="http://sms.chuangcache.com/api/sms/ordinary";
    //固定URL "http://mail.op.okjiaoyu.cn/sender/mail"
    //@Value("${mail_url}")
    public String mailUrl ="http://mail.op.okjiaoyu.cn/sender/mail";
    //@Value("${mail_tos}")
    public String mailTos = "chengwenbo@okay.cn";
//    @Autowired
//    private SMService sMService;

    @Autowired
    private SmsSendMapper smsSendMapper;

    public String insert(SmsSend record){

        record.setUserId(123);
        record.setUserName("李四");
        record.setServerName("lesson-svr");

        int num = smsSendMapper.insert(record);
        System.out.println("====="+num);
        return "surcess";

    }

    @Override
    public CommonDes sendChuangCacheMessage(RequestChuangCacheMessage request) {
        System.out.println("===sendChuangCacheMessage==");
        String mobile = request.getMobile();
        String content = request.getContent();

        //记录短信
        SmsSend record = new SmsSend();
        record.setPhone(mobile);
        record.setContent(content);
        this.insert(record);

        CommonDes commonDes;
        if (switchChuang.equals(SWITCH_FLAG)) {
            try {
               /* commonDes = transToChuangCache(mobile, content);
                return commonDes;*/
               return new CommonDes();
            } catch (Exception e) {
                sendMail("创世云短信服务异常,系统将使用移动运营商短信服务发送短信");
            }

        } else {
            LOGGER.warn("ZK配置关闭调用创世云短信服务");
        }

        if (switchMobile.equals(SWITCH_FLAG)) {
            try {
                commonDes = transToMobile(mobile, content);
                return commonDes;
            } catch (Exception e) {
                sendMail("移动运营商短信服务异常,系统无法发送短信");
                //throw new BizLayerException("", MessageErrorCode.MESSAGE_PROVIDER_ERROR);
            }
        } else {
            LOGGER.warn("ZK配置关闭调用移动短信运营商服务");
            //throw new BizLayerException("", MessageErrorCode.MESSAGE_PROVIDER_ERROR);
        }
        return null;
    }

    private CommonDes transToChuangCache(String mobile, String content) {
        try {
            long s = System.currentTimeMillis();
            String getTokenData = getToken();
            LOGGER.info("get token time: {} ", System.currentTimeMillis() - s);
            JSONObject tokenJson = JSONObject.fromObject(getTokenData);
            LOGGER.info("tokenJson: {}", tokenJson);
            int status = tokenJson.getInt("status");
            String info = tokenJson.getString("info");
            String accessToken = "";
            if (status == 1) {
                JSONObject data = tokenJson.getJSONObject("data");
                accessToken = data.getString("access_token");
                //access_token的生命周期，单位是秒数。过期之后要重新获取
                int expiresIn = data.getInt("expires_in");
                LOGGER.info("access_token:" + accessToken + ",expires_in:" + expiresIn);
            } else {
                //throw new BizLayerException("", MessageErrorCode.CHUANGCACHE_MESSAGE_ERROR);
            }
            s = System.currentTimeMillis();
            String sendSmsResult = sendSms(appKey, accessToken, mobile, content);
            LOGGER.info("sendSms time: {} ", System.currentTimeMillis() - s);

            //sendSmsResult::{"code":1000,"sendid":"2017090516580138555928044","msg":"短信提交成功"}
            LOGGER.info("sendSmsResult::" + sendSmsResult);
            JSONObject sendSmsResultJson = JSONObject.fromObject(sendSmsResult);

            int code = sendSmsResultJson.getInt("code");
            if (SEND_MESSAGE_SUCCESS == code) {
                return new CommonDes();
            } else {
                //throw new BizLayerException("", MessageErrorCode.CHUANGCACHE_MESSAGE_ERROR);
            }
        } catch (Exception e) {
            //throw new BizLayerException("", MessageErrorCode.CHUANGCACHE_MESSAGE_ERROR);
        }
        return null;
    }

    private CommonDes sendMail(String content) {
        if (!switchMail.equals(SWITCH_FLAG)) {
            LOGGER.warn("ZK配置发送邮件关闭");
            return new CommonDes();
        }
        LOGGER.error("content:{}", content);
        InetAddress address;
        String hostName = "";
        String canonicalHostName = "";
        String hostAddress = "";
        try {
            address = InetAddress.getLocalHost();
            //主机名
            hostName = address.getHostName();
            //主机别名
            canonicalHostName = address.getCanonicalHostName();
            //获取IP地址
            hostAddress = address.getHostAddress();
        } catch (UnknownHostException e) {

        }
        String subject = String.format("[%s]-%s", hostName, content);
        String message = String.format("[%s-%s-%s]-%s", hostName, canonicalHostName, hostAddress, content);
        HashMap<String, String> params = new HashMap<>(16);
        //邮件主题
        params.put("subject", subject);
        //收件人列表,多个用逗号隔开
        params.put("tos", mailTos);
        //发送内容
        params.put("content", message);

        LOGGER.info("prepare send error mail");
        try {
            LOGGER.info("params:{}", JsonUtil.obj2Json(params));
            String retStr = SendMailUtils.sendPost(mailUrl, params);
            LOGGER.info("==retStr= {}", retStr);
        } catch (Exception e) {
            LOGGER.error("send mail error,", e);
            LOGGER.error("error occurred when send message" + e.getMessage());

        }
        return new CommonDes();
    }

    private CommonDes transToMobile(String mobile, String content) {
/*
        ReqSendTempletSMS reqSendTempletSMS = new ReqSendTempletSMS();
        reqSendTempletSMS.setTemplet(content);
        reqSendTempletSMS.setMobile(mobile);*/
        //reqSendTempletSMS.setPipelineEnum(PipelineEnum.CMCC);
        /*ResponseSendSMS responseSendSMS;
        try {
            responseSendSMS = sMService.sendTempletMessage(reqSendTempletSMS);
        } catch (Exception e) {
            LOGGER.error("sMService.sendTempletMessage error param=" + JsonUtil.obj2Json(reqSendTempletSMS), e);
            throw new BizLayerException("", MessageErrorCode.MESSAGE_PROVIDER_ERROR);
        }
        if (!responseSendSMS.success() || responseSendSMS == null) {
            throw new BizLayerException("", MessageErrorCode.MESSAGE_PROVIDER_ERROR);
        }*/
        return new CommonDes();
    }


    private String getToken() {
        try {
            URL url = new URL(tokenUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            JSONObject obj = new JSONObject();
            //填写appkey (客服人员会给出)
            obj.put("appid", appId);
            //填写appsecret(客服人员会给出)
            obj.put("appsecret", appSecret);
            obj.put("grant_type", "client_credentials");
            out.writeBytes(obj.toString());
            out.flush();
            out.close();

            InputStream inStream = conn.getInputStream();
            return new String(readInputStream(inStream), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String sendSms(String appKey, String accessToken, String mobile, String content) {
        OutputStreamWriter out;
        try {
            //"http://sms.chuangcache.com/api/sms/ordinary"
            URL url = new URL(sendMSGUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject obj = new JSONObject();
            obj.put("access_token", accessToken);
            obj.put("app_key", appKey);
            //手机号码
            obj.put("mobile", mobile);
            //发送内容
            obj.put("content", content);
            obj.put("time", System.currentTimeMillis() + "");

            out = new OutputStreamWriter(conn.getOutputStream());
            out.write(obj.toString());
            out.flush();

            InputStream inStream = conn.getInputStream();
            return new String(readInputStream(inStream), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }
}
