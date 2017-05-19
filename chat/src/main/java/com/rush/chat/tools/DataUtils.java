package com.rush.chat.tools;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cfc
 * 2017/3/20.
 */
public class DataUtils {

    public static Map<String ,String > getParams(String parm){
        String[] strings = parm.split("&");
        Map<String ,String > map = new HashMap<String, String>();
        for (String str : strings){
            String[] maps = str.split("=");
            map.put(maps[0],maps[1]);
        }
        return map;
    }

    public static Object convertMap(Class type, Map map)
    {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
            Object obj = type.newInstance(); // 创建 JavaBean 对象

            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();

                if (map.containsKey(propertyName)) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = map.get(propertyName);
                    if (value != null &&!"".equals(value.toString())){
                        Object[] args = new Object[1];
                        args[0] = TypeTransformation(descriptor.getPropertyType().toString(),value);//类型转换
                        descriptor.getWriteMethod().invoke(obj, args);
                    }
                }
            }
            return obj;
        } catch (Exception e) {
            return null;
        }
    }

    public static Object TypeTransformation(String type,Object obj){
        if(obj!=null){
            if(type.contains("Long")){
                obj=Long.parseLong(obj+"");
            }else if(type.contains("Integer")){
                obj=Integer.parseInt(obj+"");
            }
        }
        return obj;
    }

    public static boolean isEmpty(String str){
        if (str != null && !str.isEmpty()){
            return false;
        }
        return true;
    }
}
