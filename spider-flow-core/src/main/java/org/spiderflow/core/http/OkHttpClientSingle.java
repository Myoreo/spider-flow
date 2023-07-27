package org.spiderflow.core.http;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

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
                    clientInstance = new OkHttpClient.Builder()
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .readTimeout(5, TimeUnit.SECONDS)
                            .writeTimeout(5, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
                            .connectionPool(new ConnectionPool())
                            .build();
                }
            }
        }
        return clientInstance;
    }
}
