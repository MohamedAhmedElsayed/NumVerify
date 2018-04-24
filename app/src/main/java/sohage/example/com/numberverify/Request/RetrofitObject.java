package sohage.example.com.numberverify.Request;

import retrofit2.Retrofit;

/**
 * Created by Mohamed_Ahmed on 16/04/2018.
 */
public class RetrofitObject {
    private static final String BASEURL = "http://apilayer.net";
    Retrofit retrofit;
    RequestAPI requestAPI;

    public RequestAPI GetRetrofitObject() {
        if (requestAPI == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASEURL).build();
            requestAPI = retrofit.create(RequestAPI.class);
        }
        return requestAPI;
    }
}
