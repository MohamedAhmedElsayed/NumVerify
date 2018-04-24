package sohage.example.com.numberverify.Request;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mohamed_Ahmed on 16/04/2018.
 */
public interface RequestAPI {//Responsibility of this class to contain all retrofit calls 

    @GET("/api/validate")
    Call<ResponseBody> VerifyNumber(@Query("access_key") String AccessKey, @Query("number") String Number);
}
