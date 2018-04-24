package sohage.example.com.numberverify.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sohage.example.com.numberverify.DButilites.DBoperations;
import sohage.example.com.numberverify.Model.ResponseModel;
import sohage.example.com.numberverify.R;
import sohage.example.com.numberverify.Model.Recycler_Adapter;
import sohage.example.com.numberverify.Request.RequestAPI;
import sohage.example.com.numberverify.Request.RetrofitObject;

public class MainActivity extends AppCompatActivity {//application main activity to unable user to enter his number and validate it 
    private static final String ACCESS_KEY = "###";// you must write your access key 
    @BindView(R.id.CountryPrefixET)
    TextInputEditText PrefixCode;
    @BindView(R.id.PhoneNumberET)
    TextInputEditText MobileNumber;
    @BindView(R.id.Result_RV)
    RecyclerView ResultRV;
    List ResultList;
    private Recycler_Adapter recyclerAdapter;
    private RequestAPI requestAPI;
    private DBoperations dBoperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);/* Butter Knife Library is used to Binding View and also to minimize number of findViewBy ID because it is costly*/
        ResultList = new ArrayList();
        dBoperations = new DBoperations(this);
        recyclerAdapter = new Recycler_Adapter(ResultList, this);
        ResultRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        ResultRV.setAdapter(recyclerAdapter);
        RetrofitObject retrofitObject = new RetrofitObject();
        requestAPI = retrofitObject.GetRetrofitObject();//to get an object from Retrofit
    }

    private Boolean ValidateInputs(String code, String number) {
        Boolean check = true;
        if (code.isEmpty()) {
            PrefixCode.setError("Required");
            check = false;
        }
        if (number.isEmpty()) {
            MobileNumber.setError("Required");
            check = false;
        }
        return check;
    }

    @OnClick(R.id.VerifyButton)// onClick Listener For Submission Button for number validation
    public void submit(View v) {
        String MobileNum = MobileNumber.getText().toString();
        String Code = PrefixCode.getText().toString();
        if (ValidateInputs(Code, MobileNum)) {
            FetchData(Code + MobileNum);
        }
    }

    @OnClick(R.id.HistoryFAB)// onClick Listener to Open History Activity
    public void HistoryActivity(View v) {
        startActivity(new Intent(this, HistoryActivity.class));
    }

    private void FetchData(String number) {// function To Request the Api using Retrofit Library and add it to recycler View  list 
        Call<ResponseBody> connection = requestAPI.VerifyNumber(ACCESS_KEY, number);
        connection.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());//convert our json to model object this also can be achieved by jsonConverter library
                    ResponseModel model = new ResponseModel();
                    model.setInternational_format(" " + jsonObject.optString("international_format", ""));
                    model.setNumber(" " + jsonObject.optString("number", ""));
                    model.setCountry_prefix(" " + jsonObject.optString("country_prefix", ""));
                    model.setCountry_code(" " + jsonObject.optString("country_code", ""));
                    model.setCountry_name(" " + jsonObject.optString("country_name", ""));
                    model.setLocation(" " + jsonObject.optString("location", ""));
                    model.setCarrier(" " + jsonObject.optString("carrier", ""));
                    model.setLine_type(" " + jsonObject.optString("line_type", ""));
                    model.setLocal_format(" " + jsonObject.optString("local_format", ""));
                    model.setValid(jsonObject.optBoolean("valid", false) + "");
                    ResultList.add(model);
                    recyclerAdapter.notifyDataSetChanged();
                    long rowsInserted = dBoperations.InsertRow(model);
                    if (rowsInserted > 0) {
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.Saved), Toast.LENGTH_LONG).show();
                    }else{//may be nit reach it
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.NotSaved), Toast.LENGTH_LONG).show();

                    }
                   // Log.e("num", jsonObject.optString("number") + jsonObject.optString("valid") + rowsInserted);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("JSONException ", e.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("IOException ", e.toString());
                }
                // Log.e("response", Response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String error = getResources().getString(R.string.ErrorNetworkConnection);
                Toast.makeText(getBaseContext(), error, Toast.LENGTH_LONG).show();
                Log.e("RequestFailure", error);
            }
        });
    }
}
