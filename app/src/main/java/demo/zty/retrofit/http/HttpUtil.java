package demo.zty.retrofit.http;

import android.text.TextUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Zty on 2016/10/18.
 */
public class HttpUtil {

    public static RequestBody multipartFormData2RequestBody(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new NullPointerException("parameter is null");
        }
        return RequestBody.create(MediaType.parse("multipart/form-data"), str);
    }

    public static RequestBody imagePng2RequestBody(File file) {
        if (file != null && file.exists()) {
            throw new NullPointerException("file is null or not exists");
        }
        return RequestBody.create(MediaType.parse("image/png"), file);
    }

}
