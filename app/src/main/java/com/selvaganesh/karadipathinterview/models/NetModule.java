package com.selvaganesh.karadipathinterview.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.selvaganesh.karadipathinterview.AppController;
import com.selvaganesh.karadipathinterview.repro.ApiSsl;
import com.selvaganesh.karadipathinterview.repro.AppRepo;
import com.selvaganesh.karadipathinterview.repro.AppRepoApi;
import com.selvaganesh.karadipathinterview.utils.ApiConstant;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private String TAG = NetModule.class.getSimpleName();
    private AppController instance;
    private Gson gson;

    public NetModule(AppController appController) {
        this.instance = appController;
        gson = new GsonBuilder().setLenient().serializeNulls().create();
    }

    @Provides
    @Singleton
    public AppRepo createAppRepo() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(180, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(ApiSsl.getInterceptor())
                .hostnameVerifier(ApiSsl.getHostnameVerifier())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
        return new AppRepo(retrofit.create(AppRepoApi.class));
    }
}

