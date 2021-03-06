package com.aggregator.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * http相关帮助类
 *
 * @author lcyan 2015-02-26 14:54
 */
@SuppressWarnings("rawtypes")
public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String postForm(String url, Map<String, String> headers,
                                  Map<String, String>form) {

        String result = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        try {
            if (headers != null) {
                for (String key : headers.keySet()) {
                    httppost.addHeader(key, headers.get(key));
                }
            }
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            for (String key : form.keySet()) {
                builder.addTextBody(key, form.get(key));
            }
            httppost.setEntity(builder.build());
            CloseableHttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                }
            }
            response.close();
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    /**
     * post请求
     *
     * @param url
     * @param headers
     * @param content
     * @return
     */
    public static String post(String url, Map<String, String> headers,
                              String content) {

        String result = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        try {
            if (headers != null) {
                for (String key : headers.keySet()) {
                    httppost.addHeader(key, headers.get(key));
                }
            }
            StringEntity sentity = new StringEntity(content, Charset.forName("UTF-8"));
            httppost.setEntity(sentity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                }
            }
            response.close();
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            return null;
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            return null;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    public static String get(String url, String content) {
        String result = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            /* *
             headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("aliyungf_tc", " AQAAAEerViwoMAsA5Uz2cmVEgkV1CKX2");
        headers.put("Accept-Language", "zh-cn");

        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("User-Agent", " MiaoPai/1 CFNetwork/808.0.2 Darwin/16.0.0");
        headers.put("Hm_lvt_e8fa5926bca558076246d7fb7ca12071", getUxixTime());
            *
            * */
            httpGet.addHeader("Hm_lvt_e8fa5926bca558076246d7fb7ca12071",Long.toString(new Date().getTime()));
            httpGet.addHeader("aliyungf_tc","AQAAAEerViwoMAsA5Uz2cmVEgkV1CKX2");
            httpGet.addHeader("Content-Type","image/png");
            httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
            CloseableHttpResponse response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                }
            }
            response.close();
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            return null;
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            return null;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取httprequest对象
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request;
    }


    /**
     * 设置cookie值
     *
     * @param response
     * @param key
     * @param value
     * @param maxAge   cookie生存期
     */
    public static void setCookieAttribute(HttpServletResponse response,
                                          String key, String value, int maxAge) {
        try {

            Cookie cookie = new Cookie(key, value);
            if (maxAge > 0)
                cookie.setMaxAge(maxAge);
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception ex) {
            //System.out.println("写cookie错误" + ex.getMessage());
        }
    }

    /**
     * 设置cookie值，默认生存期为20天
     *
     * @param response
     * @param key
     * @param value
     */
    public static void setCookieAttribute(HttpServletResponse response,
                                          String key, String value) {
        setCookieAttribute(response, key, value, 1728000);
    }





    /**
     * @param @param  controller
     * @param @return
     * @param @throws ADMSException
     * @return String
     * @throws
     * @Title: getClientIp
     * @Description: 获取客户端请求的真实ip
     * @author mengqch
     */
    public static String getClientIp(HttpServletRequest request)
            {
        if (request == null) {
            request = getRequest();
        }

        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")
                    || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;

    }



    public static Map<String, String> getRequestInfo(HttpServletRequest request)
            throws Exception {
        if (request == null)
            return null;
        Map<String, String> map = new HashMap<String, String>();
        int count = 0;
        int total = 200;
        Enumeration<String> e = request.getHeaderNames();
        String key = null;
        String value = null;
        while (e.hasMoreElements() && count < total) {
            count++;
            key = e.nextElement();
            value = request.getHeader(key);
            map.put("HTTP_HEADER_" + key, value);
        }

        count = 0;

        Map<?, ?> params = request.getParameterMap();
        Iterator<?> it = params.keySet().iterator();

        while (it.hasNext() && count < total) {
            count++;
            key = (String) it.next();
            value = request.getParameter(key);
            map.put("HTTP_PARAMETER_" + key, value);
        }
        map.put("ipaddress", request.getRemoteAddr());
        return map;
    }


    /**
     * 通过图片url返回图片Bitmap
     *
     * @param path
     * @return
     */
    public static InputStream returnBitMap(String path) {
        URL url = null;
        InputStream is = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();//利用HttpURLConnection对象,我们可以从网络中获取网页数据.  
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream(); //得到网络返回的输入流  

        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    public void inputstreamtofile(InputStream ins, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
    }
}
