package com.noriental.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JsonUtil {
    Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper mapper = new ObjectMapper();

    private JsonUtil() {
    }

    public static String obj2Json(Object obj) {
        if (obj != null) {
            try {
                return mapper.writeValueAsString(obj);
            } catch (JsonProcessingException var2) {
                var2.printStackTrace();
            }
        }

        return null;
    }

    public static byte[] obj2Byte(Object obj) {
        if (obj != null) {
            try {
                return mapper.writeValueAsBytes(obj);
            } catch (JsonProcessingException var2) {
                var2.printStackTrace();
            }
        }

        return new byte[0];
    }

    public static Map<String, Object> obj2Map(Object obj) {
        if (obj != null) {
            try {
                return (Map)mapper.readValue(mapper.writeValueAsBytes(obj), Map.class);
            } catch (JsonProcessingException var2) {
                var2.printStackTrace();
            } catch (IOException var3) {
                var3.printStackTrace();
            }
        }

        return new HashMap();
    }

    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
        if (map != null) {
            try {
                return mapper.readValue(obj2Byte(map), clazz);
            } catch (JsonProcessingException var3) {
                var3.printStackTrace();
            } catch (IOException var4) {
                var4.printStackTrace();
            }
        }

        return null;
    }

    public static <T> T readValue(String json, Class<?> clazz) {
        Object t = null;

        /*try {
            return mapper.readValue(json, clazz);
        } catch (Exception var4) {
            return t;
        }*/
        return null;
    }

    public static <T> T readValue(String json, TypeReference type) {
        Object object = null;

        try {
            object = mapper.readValue(json, type);
        } catch (Exception var4) {
            ;
        }

        //return object;
        return null;
    }

    public static String o2Jsonp(String callback, Object clazz) {
        String jsonpStr = "";
        jsonpStr = jsonpStr + callback;
        jsonpStr = jsonpStr + "(";
        jsonpStr = jsonpStr + obj2Json(clazz);
        jsonpStr = jsonpStr + ")";
        return jsonpStr;
    }

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}