package com.auxiliarybus.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Created by wangch on 2019/4/26
 */
public class JacksonUtil4J {


    public static String list2String(List<? extends Object> list) {
        String jsonlist = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonlist = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.getMessage();
        }
        return jsonlist;
    }


    /**
     * 将字符串转list对象
     *
     * @param <T>
     * @param jsonStr
     * @param cls
     * @return
     */
    public static <T> List<T> str2list(String jsonStr, Class<T> cls) {
        ObjectMapper mapper = new ObjectMapper();
        List<T> objList = null;
        try {
            JavaType t = mapper.getTypeFactory().constructParametricType(
                    List.class, cls);
            objList = mapper.readValue(jsonStr, t);
        } catch (Exception e) {
        }
        return objList;
    }

    public static <T>T str2obj(String jsonStr, Class<T> cls) {

        ObjectMapper mapper = new ObjectMapper();
        T objList  = null;
        try {
            mapper.readValue(jsonStr, cls);
        } catch (Exception e) {
        }
        return objList;
    }
}
