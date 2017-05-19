package com.rush.chat.tools;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cfc
 * 2017/3/15.
 */
public class CustNoUtils {

    private static AtomicInteger ai=new AtomicInteger(0);
    final static Integer MAXNO=1000;

    private static AtomicInteger ci=new AtomicInteger(0);
    final static Integer MAXNBR=10000;

    public static String getCustNo(){
        if(ai.get()>=MAXNO){//当累计到1000时重置为0开始
            ai.set(0);
        }
        ai.getAndIncrement();
        String custNo=String.valueOf(new Date().getTime())+String.valueOf(String.format("%04d", ai.get()));
        return "C"+custNo;
    }
}
