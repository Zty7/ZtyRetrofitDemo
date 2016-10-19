package demo.zty.retrofit.api;

import java.util.List;
import java.util.Map;

import demo.zty.retrofit.entity.Book;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Zty on 2016/10/18.
 */

public interface ApiService {

    /**
     * 1、一般get请求
     *
     * @return
     * @Url http://api.com/getBooks
     */
    @GET("getBooks")
    Call<List<Book>> getBooks();

    /**
     * 2、动态url @Path(不能用于传参)
     *
     * @param str
     * @return
     * @Url http://api.com/str/getBooks
     */
    @GET("{sid}/getBooks")
    Call<List<Book>> dynamicUrl(@Path("sid") String str);

    /**
     * 3、查询参数@Query（GET）
     *
     * @param str
     * @return
     * @url http://api.com/getBookByName?name=str
     */
    @GET("getBookByName")
    Call<Book> getBookByName(@Query("name") String str);

    /**
     * 3、查询参数@Query（POST）
     *
     * @param str
     * @param i
     * @return
     * @url http://api.com/getBookByAuthorAndYear
     */
    @POST("getBookByAuthorAndYear")
    Call<Book> getBookByAuthorAndYear(@Query("author") String str, @Query("year") Integer i);

    /**
     * 4、POST的json字符串请求体@Body
     *
     * @param book
     * @return
     * @url http://api.com/addBook
     */
    @POST("addBook")
    Call<String> addBook(@Body Book book);

    /**
     * 5、表单的方式提交键值对@FormUrlEncoded
     *
     * @param str1
     * @param str2
     * @return
     * @url http://api.com/getBookByAuthorAndName
     */
    @POST("getBookByAuthorAndName")
    @FormUrlEncoded
    Call<Book> getBookByAuthorAndName(@Field("author") String str1, @Field("name") String str2);

    /**
     * 6、单文件上传@Multipart（动态文件名）
     *
     * @param file
     * @param id
     * @return
     * @url http://api.com/addCoverById
     */
    @POST("addCoverById")
    @Multipart
    Call<String> addCoverById(@Part MultipartBody.Part file, @Part("id") RequestBody id);

    /**
     * 6、单文件上传@Multipart（固定文件名）
     *
     * @param file
     * @return
     * @url http://api.com/uploadPic
     */
    @POST("uploadPic")
    @Multipart
    Call<String> uploadPic(@Part("file\"; filename=\"filename.png") RequestBody file);

    /**
     * 7、多文件上传/多参数请求@PartMap
     *
     * @param files
     * @param params
     * @return
     * @url http://api.com/uploadPics
     */
    @POST("uploadPics")
    @Multipart
    Call<String> uploadPics(@PartMap Map<String, RequestBody> files, @PartMap Map<String, RequestBody> params);

    /**
     * 8、文件下载，建议直接用OkHttp
     *
     * @return
     * @url http://api.com/download
     */
    @GET("download")
    Call<ResponseBody> download();
}
