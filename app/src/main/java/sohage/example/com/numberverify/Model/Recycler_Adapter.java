package sohage.example.com.numberverify.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sohage.example.com.numberverify.R;

/**
 * Created by Mohamed_Ahmed on 16/04/2018.
 */
public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.holder> {
    //Recycler View Adapter to List User requests
    List<ResponseModel> VerificationList;
    Context context;

    public Recycler_Adapter(List verificationList, Context c) {
        VerificationList = verificationList;
        this.context = c;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(holder holder, int position) {
        ResponseModel model = VerificationList.get(position);
        holder.Valid.setText(context.getResources().getString(R.string.Valid) + " " + model.getValid());
        holder.Number.setText(context.getResources().getString(R.string.Number) + " " + model.getNumber());
        holder.Carrier.setText(context.getResources().getString(R.string.Carrier) + " " + model.getCarrier());
        holder.CountryCode.setText(context.getResources().getString(R.string.countryCode) + " " + model.getCountry_code());
        holder.CountryName.setText(context.getResources().getString(R.string.CountryName) + " " + model.getCountry_name());
        holder.CountryPrefix.setText(context.getResources().getString(R.string.CountryPrefix) + " " + model.getCountry_prefix());
        holder.LineType.setText(context.getResources().getString(R.string.LineType) + " " + model.getLine_type());
        holder.Location.setText(context.getResources().getString(R.string.Location) + " " + model.getLocation());
        holder.LocalFormat.setText(context.getResources().getString(R.string.LocalFormat) + " " + model.getLocation());
        holder.InternationalFormat.setText(context.getResources().getString(R.string.International_Format) + " " + model.getInternational_format());
    }

    @Override
    public int getItemCount() {
        return VerificationList.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        @BindView(R.id.ValidTV)
        TextView Valid;
        @BindView(R.id.NumberTV)
        TextView Number;
        @BindView(R.id.local_formatTV)
        TextView LocalFormat;
        @BindView(R.id.international_formatTV)
        TextView InternationalFormat;
        @BindView(R.id.locationTV)
        TextView Location;
        @BindView(R.id.line_typeTV)
        TextView LineType;
        @BindView(R.id.carrierTV)
        TextView Carrier;
        @BindView(R.id.country_codeTV)
        TextView CountryCode;
        @BindView(R.id.country_nameTV)
        TextView CountryName;
        @BindView(R.id.country_prefixTV)
        TextView CountryPrefix;

        public holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
