package com.noriental.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @author dongyl
 * @date 10:19 11/17/17
 * @project message-svr
 */
public class SendMailUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

//    public static void main(String[] args) {
//        /**
//         * 开发环境发送邮件接口
//         * 开发机需要将dns设置为10.60.0.111，或者直接写hosts
//         * 以root执行
//         * 以root权限执行
//         * 		 echo "10.60.0.111 mail.op.okjiaoyu.cn" >> /etc/hosts
//         */
//        //固定URL
//        String url = "http://mail.op.okjiaoyu.cn/sender/mail";
//        HashMap<String, String> params = new HashMap<>(16);
//        //邮件主题
//        params.put("subject", "邮件主题");
//        //收件人列表,多个用逗号隔开
//        params.put("tos", "dongyuanlong@okay.cn");
//        //发送内容
//        params.put("content", "发送的内容:message-svr Test");
//
//        try {
//            String retStr = sendPost(url, params);
//            LOGGER.info("==retStr= {}", retStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public static String sendGet(String url, HashMap<String, String> params) throws Exception {
        String result = "";
        BufferedReader in = null;
        try {
            //
            String param = parseParams(params);
            String urlNameString = url + "?" + param;
            URL getUrl = new URL(urlNameString);
            URLConnection connection = getUrl.openConnection();

            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                throw e2;
            }
        }
        return result;
    }


    public static String sendPost(String url, HashMap<String, String> params) throws Exception {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL postUrl = new URL(url);
            URLConnection connection = postUrl.openConnection();

            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.setDoOutput(true);
            connection.setDoInput(true);

            out = new PrintWriter(connection.getOutputStream());

            String param = parseParams(params);
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e2) {
                throw e2;
            }
        }
        return result;
    }


    private static String parseParams(HashMap<String, String> map) {
        StringBuffer sb = new StringBuffer();
        if (map != null) {
            for (Entry<String, String> e : map.entrySet()) {
                sb.append(e.getKey()).append("=").append(e.getValue()).append("&");
            }
            sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }
}
