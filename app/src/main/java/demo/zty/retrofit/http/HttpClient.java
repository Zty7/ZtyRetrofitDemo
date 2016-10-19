package demo.zty.retrofit.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Zty on 2016/10/18.
 */
public class HttpClient {
    private static final int CONNECT_TIMEOUT_SECONDS = 30;
    private static OkHttpClient mOkHttpClient;


    public static void init() {
        if (mOkHttpClient == null) {
            synchronized (HttpClient.class) {
                if (mOkHttpClient == null) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

                    mOkHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    public static OkHttpClient get() {
        if (mOkHttpClient == null) {
            init();
        }
        return mOkHttpClient;
    }
}
