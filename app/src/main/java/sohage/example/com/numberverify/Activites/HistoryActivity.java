package sohage.example.com.numberverify.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sohage.example.com.numberverify.DButilites.DBoperations;
import sohage.example.com.numberverify.Model.Recycler_Adapter;
import sohage.example.com.numberverify.Model.ResponseModel;
import sohage.example.com.numberverify.R;

public class HistoryActivity extends AppCompatActivity {//History Activity to Display user History 
    @BindView(R.id.HistoryRV)
    RecyclerView HistoryRV;
    private List<ResponseModel> HistoryList;
    private DBoperations dBoperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        dBoperations = new DBoperations(this);
        //  HistoryRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));// true to get the last search at first
        HistoryList = dBoperations.SelectAll();
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setStackFromEnd(true);
        linearLayout.setReverseLayout(true);
        HistoryRV.setLayoutManager(linearLayout);
        Recycler_Adapter recycler_adapter = new Recycler_Adapter(HistoryList, this);
        HistoryRV.setAdapter(recycler_adapter);
    }
}
