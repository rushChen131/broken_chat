//package com.ebeijia.im.tools;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpStatus;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.ResponseHandler;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.BasicResponseHandler;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.List;
//
///**
// * Created by cfc
// * 2015/6/19.
// */
//public class HttpClientUtils {
//    private  static Logger logger=LoggerFactory.getLogger(HttpClientUtils.class);
//    /**
//     * post提交 带返回值
//     * @param url 目标地址
//     * @param params http post提交参数
//     */
//    public static String doPostBack(String url, List<NameValuePair> params) {
//        // 创建一个默认的HttpClient
//        HttpClient httpclient = new DefaultHttpClient();
//        String result="";
//        HttpPost httppost=null;
//        try {
//            httppost = new HttpPost(url);
//            // 将POST参数以UTF-8编码并包装成表单实体对象
//            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//            // 创建响应处理器处理服务器响应内容
//            ResponseHandler<String> responseHandler = new BasicResponseHandler();
//            // 执行请求
//            result = httpclient.execute(httppost, responseHandler);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 当不再需要HttpClient实例时,关闭连接管理器以确保释放所有占用的系统资源
//            if (httpclient != null && httppost != null){
//                // 释放连接资源
//                httppost.releaseConnection();
//                // 关闭Socket连接
//                httpclient.getConnectionManager().shutdown();
//            }
//        }
//        return result;
//    }
//
//    /**
//     * httpClient  get请求
//     * @param url
//     * @param json
//     * @return
//     */
//    public static String doGetByClinet(String url) throws Exception{
//        // 创建一个默认的HttpClient
//        CloseableHttpClient  httpclient = HttpClients.createDefault();
//        StringBuilder result = new StringBuilder();
//        logger.info( "=========================url:"+url);
//        try{
//            HttpGet get = new HttpGet(url);
//            get.addHeader("Content-Type", "text/html;charset=UTF-8");
//            CloseableHttpResponse res = httpclient.execute(get);
//            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {//请求相应
//                    HttpEntity entity = res.getEntity();
//                    if (entity != null) {
//                        // 读取服务器返回的数据
//                        InputStream inputStream = entity.getContent();
//                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                        BufferedReader reader = new BufferedReader(inputStreamReader);
//                        String s;
//                        while (((s = reader.readLine()) != null)) {
//                            result.append(s);
//                        }
//                        reader.close();// 关闭输入流
//                    }
//            }else{
//                logger.info("=========================获取连接接口返回非200,返回状态为："+res.getStatusLine().getStatusCode());
//            }
//        }catch (Exception e1){
//            logger.error("获取接口工具类异常e1：",e1);
//            throw e1;
//        }finally{
//            if(httpclient!=null){
//                // 关闭连接,释放资源
//                try {
//                    httpclient.close();
//                } catch (IOException e) {
//                    logger.error("获取大接口工具类异常e1：",e);
//                    throw e;
//                }
//            }
//        }
//        return result.toString();
//    }
//
//    /**
//     * java get 请求
//     * @param url
//     * @return
//     * @throws Exception
//     */
//    public static String doGet(String url) throws Exception {
//        URL localURL = new URL(url);
//        URLConnection connection = localURL.openConnection();
//        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
//
//        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
//        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//
//        InputStream inputStream = null;
//        InputStreamReader inputStreamReader = null;
//        BufferedReader reader = null;
//        StringBuffer resultBuffer = new StringBuffer();
//        String tempLine = null;
//
//        logger.info( "HTTP Request url  is:"+url);
//        logger.info("HTTP Request return  Response code is:"+httpURLConnection.getResponseCode());
//
//        if (httpURLConnection.getResponseCode() >= 300) {
//            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
//        }
//
//        try {
//            inputStream = httpURLConnection.getInputStream();
//            inputStreamReader = new InputStreamReader(inputStream);
//            reader = new BufferedReader(inputStreamReader);
//
//            while ((tempLine = reader.readLine()) != null) {
//                resultBuffer.append(tempLine);
//            }
//
//        } finally {
//
//            if (reader != null) {
//                reader.close();
//            }
//            if (inputStreamReader != null) {
//                inputStreamReader.close();
//            }
//            if (inputStream != null) {
//                inputStream.close();
//            }
//        }
//        return resultBuffer.toString();
//    }
//
//}
