package demo.zty.retrofit.api;

import demo.zty.retrofit.http.HttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zty on 2016/10/18.
 */

public class ApiInit {
    private static final String BASE_URL = "http://api.com/";
    private ApiService mApiService;

    public ApiInit() {
        initApiService();
    }

    private void initApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                // http请求的baseUrl
                .baseUrl(BASE_URL)
                // 指定HttpClient
                .client(HttpClient.get())
                // 指定解析工具
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    ApiService getApiServices() {
        if (mApiService == null) {
            throw new NullPointerException("ApiService is null");
        }
        return mApiService;
    }

    public static ApiInit get() {
        return ApiHolder.mApiInit;
    }

    private static final class ApiHolder {
        private static final ApiInit mApiInit = new ApiInit();
    }
}
