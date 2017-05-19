package com.rush.chat.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* DateTime for JAVA (日期时间处理)
*
* @Author jinjian.feng
* @version 1.0
*/
public class DateTimeUtils {


	private SimpleDateFormat sysTimeFormat = new SimpleDateFormat("HHmmss");
	private SimpleDateFormat showDateFormatZHCN = new SimpleDateFormat(
			"yyyy年MM月dd日 HH时mm分ss秒");

    /**
     * 获得系统当前日期时间
     *
     * @return
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat sysDateFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        return sysDateFormat.format(new Date());
    }



}