//package org.spiderflow.core.http;
//
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.RequestBody;
//import com.squareup.okhttp.Response;
//
//import java.util.Map;
//
///**
// * @Author : xuaoping
// * @Date : 2023/07
// */
//public class OkHttpUtil {
//    /**
//     * 普通get请求
//     *
//     * @param url
//     * @return
//     * @throws Exception
//     */
//    public static String get(String url) throws Exception {
//        return get(url, null);
//    }
//
//    /**
//     * 普通get请求  带header
//     *
//     * @param url
//     * @return
//     * @throws Exception
//     */
//    public static String get(String url, Map<String, String> headers) throws Exception {
//        OkHttpClient clientInstance = OkHttpClientSingle.getClientInstance();
//
//        Request.Builder builder = new Request.Builder().url(url);
//        if (headers != null) {
//            for (String key : headers.keySet()) {
//                builder.addHeader(key, headers.get(key));
//            }
//        }
//        Request request = builder.build();
//
//        Response res = clientInstance.newCall(request).execute();
//        if (!res.isSuccessful()) {
//            //log.warn("url:{} header:{} msg:{}", url, JSON.toJSONString(headers), res.message());
//            return null;
//        }
//        return res.body().string();
//    }
//
//    /**
//     * post 请求带formdata
//     *
//     * @param url
//     * @param formData
//     * @return
//     * @throws Exception
//     */
//    public static String postForm(String url, Map<String, String> formData) throws Exception {
//        return postForm(url, null, formData);
//    }
//
//    /**
//     * post 请求带formdata  带header
//     *
//     * @param url
//     * @param headers
//     * @param formData
//     * @return
//     * @throws Exception
//     */
//    public static String postForm(String url, Map<String, String> headers, Map<String, String> formData) throws Exception {
//        Response response = postFormRetRes(url, headers, formData);
//        if (!response.isSuccessful()) {
//            //log.warn("url:{} header:{} formData:{} msg:{}", url, JSON.toJSONString(headers), JSON.toJSONString(formData), response.message());
//            return null;
//        }
//        return response.body().string();
//    }
//
//    /**
//     * post 请求带formdata  带header  返回response
//     *
//     * @param url
//     * @param headers
//     * @param formData
//     * @return
//     * @throws Exception
//     */
//    private static Response postFormRetRes(String url, Map<String, String> headers, Map<String, String> formData) throws Exception {
//        OkHttpClient clientInstance = OkHttpClientSingle.getClientInstance();
//
//        RequestBody requestBody = n;
//
//        FormBody.Builder builder = new FormBody.Builder();
//        if (formData != null) {
//            for (String key : formData.keySet()) {
//                builder.add(key, formData.get(key));
//            }
//            requestBody = builder.build();
//        }
//
//        Request.Builder requestBuilder = new Request.Builder().url(url);
//        if (requestBody != null) {
//            requestBuilder.post(requestBody);
//        }
//        if (headers != null) {
//            for (String key : headers.keySet()) {
//                requestBuilder.addHeader(key, headers.get(key));
//            }
//        }
//        Request request = requestBuilder.build();
//        Response res = clientInstance.newCall(request).execute();
//        return res;
//    }
//
//    /**
//     * post 请求带body json数据  返回string
//     *
//     * @param url
//     * @param data
//     * @return
//     * @throws Exception
//     */
//    public static String postBody(String url, Object data) throws Exception {
//        Response response = postBodyRetRes(url, null, data);
//        if (!response.isSuccessful()) {
//            log.warn("url:{}  body:{} msg:{}", url, JSON.toJSONString(data), response.message());
//            return null;
//        }
//        return response.body().string();
//    }
//
//    /**
//     * post 请求带body json数据  带header  返回string
//     *
//     * @param url
//     * @param headers
//     * @param data
//     * @return
//     * @throws Exception
//     */
//    public static String postBody(String url, Map<String, String> headers, Object data) throws Exception {
//        Response response = postBodyRetRes(url, headers, data);
//        if (!response.isSuccessful()) {
//            log.warn("url:{} header:{} body:{} msg:{}", url, JSON.toJSONString(headers), JSON.toJSONString(data), response.message());
//            return null;
//        }
//        return response.body().string();
//    }
//
//    /**
//     * post 请求带body json数据  带header  返回response
//     *
//     * @param url
//     * @param headers
//     * @param data
//     * @return
//     * @throws Exception
//     */
//    public static Response postBodyRetRes(String url, Map<String, String> headers, Object data) throws Exception {
//        OkHttpClient clientInstance = OkHttpClientSingle.getClientInstance();
//
//        MediaType mediaType = MediaType.parse("application/json; charset=UTF-8");
//
//        RequestBody requestBody = FormBody.create(mediaType, JSON.toJSONString(data));
//
//        Request.Builder requestBuilder = new Request.Builder().url(url);
//        if (requestBody != null) {
//            requestBuilder.post(requestBody);
//        }
//        if (headers != null) {
//            for (String key : headers.keySet()) {
//                requestBuilder.addHeader(key, headers.get(key));
//            }
//        }
//        Request request = requestBuilder.build();
//        Response res = clientInstance.newCall(request).execute();
//        return res;
//    }
//}
