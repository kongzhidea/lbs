package com.kk.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClient调用工具类
 */
public class HttpClientUtil {
    private static final Log logger = LogFactory.getLog("httpClient");

    /**
     * 发送GET请求
     *
     * @param uri
     * @return
     */
    public static String sendGet(String uri) {
        String responseBody = null;
        HttpClient httpClient = new DefaultHttpClient();
        // 设置超时时间
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                10000);
        try {
            // URLEncoder.encode(uri, "utf-8");
            HttpGet httpGet = new HttpGet(uri);
            logger.info("executing request " + httpGet.getURI());
            // Create a response handler
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            responseBody = httpClient.execute(httpGet, responseHandler);
            logger.info(responseBody);
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseBody;
    }

    /**
     * 发送GET请求,返回字节数组
     *
     * @param uri
     * @return
     */
    public static byte[] sendGetReturnByte(String uri) {
        byte[] output = null;
        HttpClient httpClient = new DefaultHttpClient();
        // 设置超时时间
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                10000);
        try {
            HttpGet httpGet = new HttpGet(uri);
            HttpResponse responseBody = httpClient.execute(httpGet);
            HttpEntity entry = responseBody.getEntity();
            InputStream input = entry.getContent();
            output = IOUtils.toByteArray(input);
            input.close();
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return output;
    }

    /**
     * 发送POST请求
     *
     * @param uri
     * @param paramMap 请求参数
     * @return
     */
    public static String sendPost(String uri, Map<String, String> paramMap) {
        return sendPost(uri, paramMap, null);
    }

    /**
     * 发送POST请求
     *
     * @param uri
     * @param paramMap 请求参数
     * @param charset  参数编码
     * @return
     */
    public static String sendPost(String uri, Map<String, String> paramMap,
                                  String charset) {
        String responseBody = null;
        HttpClient httpClient = new DefaultHttpClient();
        // 设置超时时间
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                10000);
        try {
            HttpPost httpPost = new HttpPost(uri);
            logger.info("executing request " + httpPost.getURI());
            if (paramMap != null) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>(
                        paramMap.size());
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    NameValuePair nvp = new BasicNameValuePair(entry.getKey(),
                            entry.getValue());
                    nvps.add(nvp);
                }
                if (charset != null) {
                    httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
                } else {
                    httpPost.setEntity(new UrlEncodedFormEntity(nvps));
                }
            }
            // Create a response handler
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            responseBody = httpClient.execute(httpPost, responseHandler);
            logger.info("----------------------------------------");
            logger.info(responseBody);
            logger.info("----------------------------------------");
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseBody;

    }

    /**
     * Extends the post data
     */
    public static String sendPostRequest(String _url, String data) {

        try {
            // Send the request
            URL url = new URL(_url);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(
                    conn.getOutputStream());

            // write parameters
            if (data != null) {
                writer.write(data);
            }
            writer.flush();

            // Get the response
            StringBuffer answer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            writer.close();
            reader.close();

            // Output the response
            return answer.toString();

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    public static void main(String[] args) {
        //String url = "https://sp0.baidu.com/9_Q4sjW91Qh3otqbppnN2DJv/pae/traffic/api/query?cb=jQuery110201718641787301749_1456194334956&city=beijing&hphm=%E4%BA%ACNJ5765&hpzl=02&engineno=AA82460&classno=&registno=&cityname=%E5%8C%97%E4%BA%AC&provincename=%E5%8C%97%E4%BA%AC&format=json&time_used=0&_=1456194334997";
        String url = "https://sp0.baidu.com/9_Q4sjW91Qh3otqbppnN2DJv/pae/traffic/api/query?cb=jQuery1102019041644595563412_1451547100971&_=1451547100975&format=json&hpzl=02&time_used=0&city=beijing&hphm=%E4%BA%ACNJ5765&engineno=AA82460&classno=&registno=null&cityname=%E5%8C%97%E4%BA%AC&provincename=%E5%8C%97%E4%BA%AC";
        try {
            System.out.println(URLEncoder.encode("京","utf-8"));
            System.out.println(URLEncoder.encode("北京","utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String responseBody = null;
        HttpClient httpClient = new DefaultHttpClient();
        // 设置超时时间
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                10000);


        try {
            // URLEncoder.encode(uri, "utf-8");
            HttpGet httpGet = new HttpGet(url);

            httpGet.addHeader("Accept", "*/*");
            httpGet.addHeader("Accept-Encoding", "gzip, deflate, sdch");
            httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpGet.addHeader("Connection", "keep-alive");
            httpGet.addHeader("Cookie", "BIDUPSID=DBBB6A80013785B2EE5F00405B1C6A0C; PSTM=1450862107; BAIDUID=1D5021BAEB36831E25FB6995A85AD65C:FG=1; BDSFRCVID=V9_sJeCCxG3zESn4ABF6SHFfG1zO9HUn4X-Y3J; H_BDCLCKID_SF=tRk8oItMJCvSjt51-tTaMDCV-frb-C62aKDs-DT7-hcqEpO9QTb0QqtHj-QG0j5gWgchKKjLBKT1SJumWKcZDUTh-p52f60etJ-D3J; BAIDU_DUP_lcr=https://www.google.com.hk/; H_PS_PSSID=18451_1444_17710_18535_12826_10212_18730_18545_18559_17000_17072_15546_11472");
            //String cookie =              "BIDUPSID=DBBB6A80013785B2EE5F00405B1C6A0C; PSTM=1450862107; BAIDUID=1D5021BAEB36831E25FB6995A85AD65C:FG=1; BDSFRCVID=V9_sJeCCxG3zESn4ABF6SHFfG1zO9HUn4X-Y3J; H_BDCLCKID_SF=tRk8oItMJCvSjt51-tTaMDCV-frb-C62aKDs-DT7-hcqEpO9QTb0QqtHj-QG0j5gWgchKKjLBKT1SJumWKcZDUTh-p52f60etJ-D3J; BAIDU_DUP_lcr=https://www.google.com.hk/; H_PS_PSSID=18451_1444_17710_18535_12826_10212_18730_18545_18559_17000_17072_15546_11472";
            httpGet.addHeader("Host", "sp0.baidu.com");
            httpGet.addHeader("Referer", "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=%E8%BF%9D%E7%AB%A0&rsv_pq=e5a5f11d00086808&rsv_t=c5f8NETidsWwwxl49y7h8kf5wTBskA1YMa%2BGnnSRduYbHUUsCUjrNPXLRcQ&rsv_enter=1&rsv_sug3=7&rsv_sug1=4&rsv_sug2=0&rsv_sug7=100&inputT=1312&rsv_sug4=3489");
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");


            logger.info("executing request " + httpGet.getURI());
            // Create a response handler
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            responseBody = httpClient.execute(httpGet, responseHandler);
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        System.out.println(responseBody);
    }

}
