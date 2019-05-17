package com.largeScreen.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class GlobalUtil {

    public static String PackResponse(Object obj){
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }
}
