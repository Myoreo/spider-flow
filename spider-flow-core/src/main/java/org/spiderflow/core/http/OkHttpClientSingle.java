package org.spiderflow.core.http;

import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @Author : xuaoping
 * @Date : 2023/07
 */
public class OkHttpClientSingle {

    private OkHttpClientSingle() {
    }

    private static volatile OkHttpClient clientInstance;

    public static OkHttpClient getClientInstance() {
        if (clientInstance == null) {
            synchronized (OkHttpClientSingle.class) {
                if (clientInstance == null) {
                    clientInstance = new OkHttpClient();
                    clientInstance.setConnectTimeout(5, TimeUnit.SECONDS);
                    clientInstance.setWriteTimeout(5, TimeUnit.SECONDS);
                    clientInstance.setReadTimeout(15, TimeUnit.SECONDS);
                    clientInstance.setConnectionPool(new ConnectionPool(10, 5, TimeUnit.MILLISECONDS));
                    clientInstance.setRetryOnConnectionFailure(true);
                }
            }
        }
        return clientInstance;
    }
}
