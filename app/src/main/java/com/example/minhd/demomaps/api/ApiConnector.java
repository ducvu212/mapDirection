package com.example.minhd.demomaps.api;

import com.example.minhd.demomaps.BuildConfig;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiConnector {
    private static ApiConnector instance;
    private Res res;

    private ApiConnector() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .addHeader("version", BuildConfig.VERSION_NAME)
                .build());

        builder.connectTimeout(2, TimeUnit.SECONDS)
                .readTimeout(2, TimeUnit.SECONDS)
                .writeTimeout(2, TimeUnit.SECONDS);
        Retrofit.Builder builderRetrofit = new Retrofit.Builder();
        builderRetrofit
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .client(builder.build());
        Retrofit retrofit = builderRetrofit.build();
        res = retrofit.create(Res.class);
    }

    public synchronized static ApiConnector getInstance() {
        if ( instance == null ) {
            instance = new ApiConnector();
        }
        return instance;
    }

    public Observable<SearchAutocompletePlaceReponse> searchPlace(String input) {
        return res.searchPlace("AIzaSyC4d4Yfc4Gzg5nEFmkMBRE_Yf9nJWCdME8",
                input, "vi")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
