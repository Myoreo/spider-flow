package org.spiderflow.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import okhttp3.MediaType;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

/**
 * 请求对象包装类
 *
 * @author Administrator
 */
public interface HttpRequest {

	HttpRequest url(String url);

	HttpRequest headers(Map<String, String> headers);

    HttpRequest header(String key, String value);

    HttpRequest header(String key, Object value);

    HttpRequest cookies(Map<String, String> cookies);

    HttpRequest cookie(String name, String value);

    HttpRequest mediaType(MediaType mediaType);

    HttpRequest data(String key, String value);

    HttpRequest data(String key, Object value);

    HttpRequest data(Object body);

    HttpRequest followRedirect(boolean followRedirects);

    HttpRequest timeout(int timeout);

    HttpRequest proxy(String host, int port);

    @SuppressWarnings("deprecation")
    HttpRequest validateTLSCertificates(boolean value);

    HttpResponse execute() throws IOException;
}
