package org.spiderflow.core.io;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author : xuaoping
 * @Date : 2023/07
 */
public class PostHttpRequest implements HttpRequest {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public static final MediaType TEXT
            = MediaType.get("text/plain; charset=utf-8");

    public static final MediaType FORM_DATA
            = MultipartBody.FORM;

    public static final MediaType FORM_URLENCODED
            = MediaType.get("application/x-www-form-urlencoded");

    private MediaType mediaType;

    private HttpUrl.Builder urlBuilder;

    private Request.Builder reqBuild;

    private Map<String, String> cookies;

    private String body;

    private Map<String, String> formParams;

    private OkHttpClient.Builder httpClientBuilder;

    public PostHttpRequest() {
        reqBuild = new Request.Builder();
        new FormBody.Builder();
        this.cookies = new HashMap<>();
        httpClientBuilder = new OkHttpClient.Builder().retryOnConnectionFailure(true);
    }

    @Override
    public HttpRequest url(String url) {
        urlBuilder = HttpUrl.parse(url).newBuilder();
        return this;
    }

    @Override
    public HttpRequest headers(Map<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            reqBuild.header(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override
    public HttpRequest header(String key, String value) {
        reqBuild.header(key, value);
        return this;
    }

    @Override
    public HttpRequest header(String key, Object value) {
        reqBuild.header(key, value.toString());
        return this;
    }

    @Override
    public HttpRequest cookies(Map<String, String> cookies) {
        this.cookies.putAll(cookies);
        return this;
    }


    @Override
    public HttpRequest cookie(String name, String value) {
        this.cookies.put(name, value);
        return this;
    }

    @Override
    public HttpRequest mediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        if (mediaType == FORM_DATA || mediaType == FORM_URLENCODED) {
            formParams = new HashMap<>();
        }
        return this;
    }

    @Override
    public HttpRequest data(String key, String value) {
        if (formParams != null) {
            formParams.put(key, value);
        } else {
            urlBuilder.addQueryParameter(key, value);
        }
        return this;
    }

    @Override
    public HttpRequest data(String key, Object value) {
        if (formParams != null) {
            formParams.put(key, value.toString());
        } else {
            urlBuilder.addQueryParameter(key, value.toString());
        }
        return this;
    }

    @Override
    public HttpRequest data(Object body) {
        if (!body.getClass().equals(String.class)) {
            this.body = JSONObject.toJSONString(body);
        } else {
            this.body = body.toString();
        }
        return this;
    }

    @Override
    public HttpRequest followRedirect(boolean followRedirects) {
        httpClientBuilder.followRedirects(followRedirects);
        return this;
    }

    @Override
    public HttpRequest timeout(int timeout) {
        httpClientBuilder.connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS);
        return this;
    }

    @Override
    public HttpRequest proxy(String host, int port) {
        return this;
    }

    @Override
    public HttpRequest validateTLSCertificates(boolean value) {
        return this;
    }

    @Override
    public HttpResponse execute() throws IOException {
        reqBuild.url(urlBuilder.build());
        if (!cookies.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, String> entry : cookies.entrySet()) {
                stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reqBuild.header("Cookie", stringBuilder.toString());
        }
        RequestBody requestBody;
        if (FORM_DATA == mediaType) {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(FORM_DATA);
            formParams.forEach(builder::addFormDataPart);
            requestBody = builder.build();
        } else if (FORM_URLENCODED == mediaType) {
            FormBody.Builder builder = new FormBody.Builder();
            formParams.forEach(builder::add);
            requestBody = builder.build();
        } else {
            requestBody = RequestBody.create(mediaType, body == null ? "" : body);
        }
        Request request = reqBuild.post(requestBody).build();
        OkHttpClient clientInstance = httpClientBuilder.build();
        Response response = clientInstance.newCall(request).execute();
        return new HttpResponse(response);
    }
}
