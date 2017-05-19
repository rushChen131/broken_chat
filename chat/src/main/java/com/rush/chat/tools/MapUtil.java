package com.rush.chat.tools;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by lzw on 2016/12/24.
 */
public class MapUtil {
    /**
     * map转bean
     * @param type
     * @param map
     * @return
     * @throws java.beans.IntrospectionException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static Object convertMap(Class type, Map map){
        try{
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
                    Object[] args = new Object[1];
                    args[0] = TypeTransformation(descriptor.getPropertyType().toString(),value);//类型转换
                    descriptor.getWriteMethod().invoke(obj, args);
                }
            }
            return obj;
        }catch (Exception e){
            return null;
        }
    }


    /**
     * 类型转换
     * @param type
     * @param obj
     * @return
     */
    public static Object TypeTransformation(String type,Object obj){
        if(obj!=null){
            if(type.contains("Long")){
                if("".equals(obj)){
                    obj=null;
                }else{
                    obj=Long.parseLong(obj+"");
                }
            }else if(type.contains("Integer")){
                if("".equals(obj)){
                    obj=null;
                }else{
                    obj=Integer.parseInt(obj+"");
                }
            }
        }
        return obj;
    }
    
    /**
     * 清除空字符串或空值
     * @param data
     */
    public static void clearNull(Map<String,Object> data){
    	if(data == null){
    		return;
    	}
    	Iterator<Entry<String, Object>> it = data.entrySet().iterator();
    	while(it.hasNext()){
    		Entry<String, Object> entry = it.next();
    		Object value = entry.getValue();
    		if(value == null || value.toString().equals("")){
    			it.remove();
    		}
    	}
    }

}
