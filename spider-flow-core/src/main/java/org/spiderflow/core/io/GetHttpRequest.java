package org.spiderflow.core.io;

import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author : xuaoping
 * @Date : 2023/07
 */
public class GetHttpRequest implements HttpRequest {

    private HttpUrl.Builder urlBuilder;

    private Request.Builder reqBuild;

    private OkHttpClient.Builder httpClientBuilder;

    private Map<String, String> cookies;

    public GetHttpRequest() {
        reqBuild = new Request.Builder().get();
        cookies = new HashMap<>();
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
        return this;
    }

    @Override
    public HttpRequest data(String key, String value) {
        urlBuilder.addQueryParameter(key, value);
        return this;
    }

    @Override
    public HttpRequest data(String key, Object value) {
        if (value != null) {
            urlBuilder.addQueryParameter(key, value.toString());
        }
        return this;
    }

    @Override
    public HttpRequest data(Object body) {
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
        Request request = reqBuild.get().build();
        OkHttpClient clientInstance = httpClientBuilder.build();
        Response response = clientInstance.newCall(request).execute();
        return new HttpResponse(response);
    }
}
