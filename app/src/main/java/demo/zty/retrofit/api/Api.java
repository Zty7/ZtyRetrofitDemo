package demo.zty.retrofit.api;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.zty.retrofit.entity.Book;
import demo.zty.retrofit.http.HttpUtil;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Zty on 2016/10/18.
 */

public class Api {
    private static final String TAG = "Api";

    public static void getBooks() {
        Call<List<Book>> call = getApiService().getBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response != null && response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "getBooks onResponse: " + response.body().toString());
                    List<Book> books = response.body();
                    // to do something ...
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static void dynamicUrl() {
        try {
            Call<List<Book>> call = getApiService().dynamicUrl("sid");
            Response<List<Book>> response = call.execute();
            List<Book> books = response.body();
            // to do something ...
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getBookByName() {
        Call<Book> call = getApiService().getBookByName("name");
    }

    public static void getBookByAuthorAndYear() {
        Call<Book> call = getApiService().getBookByAuthorAndYear("author", 2016);
    }

    public static void addBook() {
        Book book = new Book();
        book.setAuthor("author");
        book.setName("name");
        Call<String> call = getApiService().addBook(book);
    }

    public static void getBookByAuthorAndName() {
        Call<Book> call = getApiService().getBookByAuthorAndName("author", "name");
    }

    public static void addCoverById() {
        File file = new File(Environment.getExternalStorageDirectory(), "icon.png");
        RequestBody requestBody = HttpUtil.imagePng2RequestBody(file);
        MultipartBody.Part cover = MultipartBody.Part.createFormData("photos", "icon.png", requestBody);
        Call<String> call = getApiService().addCoverById(cover, HttpUtil.multipartFormData2RequestBody("id"));
    }

    public static void uploadPic() {
        File file = new File(Environment.getExternalStorageDirectory(), "icon.png");
        Call<String> call = getApiService().uploadPic(HttpUtil.imagePng2RequestBody(file));
    }

    public static void uploadPics() {
        File file1 = new File(Environment.getExternalStorageDirectory(), "icon1.png");
        File file2 = new File(Environment.getExternalStorageDirectory(), "icon2.png");
        File file3 = new File(Environment.getExternalStorageDirectory(), "icon3.png");
        Map<String, RequestBody> files = new HashMap<String, RequestBody>();
        files.put("photos\"; filename=\"icon.png", HttpUtil.imagePng2RequestBody(file1));
        files.put("photos\"; filename=\"icon.png", HttpUtil.imagePng2RequestBody(file2));
        files.put("photos\"; filename=\"icon.png", HttpUtil.imagePng2RequestBody(file3));
        Map<String, RequestBody> params = new HashMap<String, RequestBody>();
        params.put("key1", HttpUtil.multipartFormData2RequestBody("param1"));
        params.put("key2", HttpUtil.multipartFormData2RequestBody("param2"));
        Call<String> call = getApiService().uploadPics(files, params);
    }

    public static void download() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // IO操作要放在子线程进行
                try {
                    Call<ResponseBody> call = getApiService().download();
                    Response<ResponseBody> response = call.execute();
                    InputStream is = response.body().byteStream();
                    // save file
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static ApiService getApiService() {
        return ApiInit.get().getApiServices();
    }

}
