package com.selvaganesh.skilllync.repro;

import com.selvaganesh.skilllync.utils.UiUtils;

import javax.net.ssl.HostnameVerifier;

import okhttp3.Interceptor;
import okhttp3.Request;

public class ApiSsl {

    public static HostnameVerifier getHostnameVerifier() {
        return (hostname, session) -> true;
    }


    public static Interceptor getInterceptor() {
        return chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("x-api-key","352e1728bc6344d897c1023f7b9b969c5")
                  //  .header("x-api-key", "352e1728bc6344d897c1023f7b9b969c5")
                    .method(original.method(), original.body());
            Request request = requestBuilder.build();
            for (int i = 0; i < request.headers().size(); i++) {
                UiUtils.appLog("BofinApiSsl", "\n\nName:" + request.headers().name(i) + "\tValue:" + request.headers().value(i) + "\n\n");
            }
            return chain.proceed(request);
        };
    }
}
