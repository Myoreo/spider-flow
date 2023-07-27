package org.spiderflow.core.io;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.spiderflow.io.SpiderResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 响应对象包装类
 *
 * @author Administrator
 */
@Slf4j
public class HttpResponse implements SpiderResponse {

    private Response response;

    private int statusCode;

    private String urlLink;

    private String htmlValue;

    private String titleName;

    private Object jsonValue;

    public HttpResponse(Response response) {
        super();
        this.response = response;
        this.statusCode = response.code();
        this.urlLink = response.request().url().toString();
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getTitle() {
        if (titleName == null) {
            synchronized (this) {
                titleName = Jsoup.parse(getHtml()).title();
            }
        }
        return titleName;
    }

    @Override
    public String getHtml() {
        if (htmlValue == null) {
            synchronized (this) {
                try {
                    htmlValue = response.body().string();
                } catch (IOException e) {
                    return "";
                }
            }
        }
        return htmlValue;
    }

    @Override
    public Object getJson() {
        if (jsonValue == null) {
            jsonValue = JSON.parse(getHtml());
        }
        return jsonValue;
    }

    @Override
    public Map<String, String> getCookies() {
        Map<String, String> cookieMap = new HashMap<>();
        List<String> cookies = response.headers("Set-Cookie");
        try {
            for (String cookie : cookies) {
                int size = cookie.length();
                int i = cookie.indexOf(";");
                if (i < size && i >= 0) {
                    //最终获取到的cookie
                    String str = cookie.substring(0, i);
                    String[] keyAndValue = str.split("=");
                    if (keyAndValue.length == 2) {
                        cookieMap.put(keyAndValue[0], keyAndValue[1]);
                    }
                }
            }
        } catch (Exception e) {
            log.error("get cookie error {}", cookies, e);
        }
        return cookieMap;
    }

    @Override
    public Map<String, String> getHeaders() {
        //todo
        return Collections.emptyMap();
        //return response.headers().toMultimap();
    }

    @Override
    public byte[] getBytes() {
        try {
            return response.body().bytes();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Override
    public String getContentType() {
        return response.header("content-type");
    }

    @Override
    public void setCharset(String charset) {
        //this.response.charset(charset);
    }

    @Override
    public String getUrl() {
        return urlLink;
    }

    @Override
    public InputStream getStream() {
        return response.body().byteStream();
    }
}
