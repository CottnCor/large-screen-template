package com.largeScreen.api.util;

import org.mybatis.spring.annotation.MapperScan;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EntityUtil {

    public static Map<String, Object> easyMap(Map<String, String[]> object) {
        Map returnMap = new HashMap();
        Iterator entries = object.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i = 0; i < values.length; i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 实体类转Map
     * @param object
     * @return
     */
    public static Map<String, Object> entityToMap(Object object) {
        Map<String, Object> map = new HashMap();
        for (Field field : object.getClass().getDeclaredFields()){
            try {
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                Object o = field.get(object);
                map.put(field.getName(), o);
                field.setAccessible(flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * Map转实体类
     * @param map 需要初始化的数据，key字段必须与实体类的成员名字一样，否则赋值为空
     * @param entity  需要转化成的实体类
     * @return
     */
    public static <T> T mapToEntity(Map<String, Object> map, Class<T> entity) {
        T t = null;
        try {
            t = entity.newInstance();
            for(Field field : entity.getDeclaredFields()) {
                if (map.containsKey(field.getName())) {
                    boolean flag = field.isAccessible();
                    field.setAccessible(true);
                    Object object = map.get(field.getName());
                    if (object!= null) {
                        if(field.getType() == Integer.class){
                            field.set(t, Integer.parseInt(object.toString()));
                        } else if(field.getType() == Long.class){
                            field.set(t, Long.parseLong(object.toString()));
                        } else if(field.getType() == Double.class){
                            field.set(t, Double.parseDouble(object.toString()));
                        } else if(field.getType() == Float.class){
                            field.set(t, Float.parseFloat(object.toString()));
                        } else if(field.getType() == Boolean.class){
                            field.set(t, Boolean.getBoolean(object.toString()));
                        } else{
                            field.set(t, object.toString());
                        }
                    }
                    field.setAccessible(flag);
                }
            }
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
