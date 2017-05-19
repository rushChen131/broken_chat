package com.rush.chat.models.common;


import java.util.HashMap;
import java.util.Map;

public class Order {

    private static Map<String,String> ORDER_CACHE = new HashMap<String, String>();

    private String column;
    private String orderType;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    private Order(String column, String orderType){
        String order = ORDER_CACHE.get(column);
        if (order==null){
            order = column;
            ORDER_CACHE.put("${column}",order);
        }
        this.column = order;
        this.orderType=orderType;
    }

    public static Order desc(String column){
        Order order=new Order(column, "desc");
        return order;
    }

    public static Order asc(String column){
        Order order=new Order( column, "asc");
        return order;
    }


    public String toString(){
        String orderColumn=this.column;
        StringBuffer sb=new StringBuffer();
        sb.append(orderColumn)
                .append(" ")
                .append(this.orderType);

        return sb.toString();
    }
}
