package org.spiderflow.core.io;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @Author : xuaoping
 * @Date : 2023/07
 */
public class GetHttpRequest extends HttpRequest {

    private HttpUrl.Builder urlBuilder;

    private Request.Builder reqBuild;

    public GetHttpRequest() {
        reqBuild = new Request.Builder().get();
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
        return this;
    }

    @Override
    public HttpRequest cookie(String name, String value) {
        return this;
    }

    @Override
    public HttpRequest contentType(String contentType) {
        return this;
    }

    @Override
    public HttpRequest data(String key, String value) {
        return this;
    }

    @Override
    public HttpRequest data(String key, Object value) {
        return this;
    }

    @Override
    public HttpRequest data(String key, String filename, InputStream is) {
        return this;
    }

    @Override
    public HttpRequest data(Object body) {
        return this;
    }

    @Override
    public HttpRequest data(Map<String, String> data) {
        return this;
    }

    @Override
    public HttpRequest method(String method) {
        return this;
    }

    @Override
    public HttpRequest followRedirect(boolean followRedirects) {
        return this;
    }

    @Override
    public HttpRequest timeout(int timeout) {
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
        Request request = reqBuild.build();
        OkHttpClient clientInstance = new OkHttpClient();
        Response response = clientInstance.newCall(request).execute();

        return super.execute();
    }
}
