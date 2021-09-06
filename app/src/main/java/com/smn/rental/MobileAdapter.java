package com.smn.rental;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MobileAdapter extends RecyclerView.Adapter<MobileAdapter.MobileViewHolder> {

    Context context;
    ArrayList<MobileUser> mobiles;
    private View.OnClickListener clickListener;

    public MobileAdapter(Context context, ArrayList<MobileUser> mobiles, View.OnClickListener clickListener) {
        this.context = context;
        this.mobiles = mobiles;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MobileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_row, parent, false);
        return new MobileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MobileViewHolder holder, int position) {
        MobileUser mobileUser = mobiles.get(position);
        holder.txtMobileName.setText(mobileUser.mobile.name);
        holder.txtMobilePrice.setText("Price: $" + mobileUser.mobile.price);
        holder.txtRentPrice.setText("Rent: $" + String.format("%.2f", (mobileUser.mobile.price / 100 * 15)));
        holder.imageMobile.setImageResource(ImageList.getImageFromName(mobileUser.mobile.imagename));
        holder.buttonViewDetails.setTag(mobileUser);
        holder.btRent.setTag(mobileUser.mobile);
        if (TextUtils.isEmpty(mobileUser.username)) {
            holder.btRent.setVisibility(View.VISIBLE);
            holder.tvStatus.setText("Available");
            holder.tvStatus.setTextColor(Color.GREEN);
            holder.tvRentedBy.setText("");
        } else {
            holder.btRent.setVisibility(View.GONE);
            holder.tvStatus.setText("Not Available");
            holder.tvStatus.setTextColor(Color.RED);
            holder.tvRentedBy.setText("Rented by " + mobileUser.username);
        }
    }

    @Override
    public int getItemCount() {
        return mobiles.size();
    }

    public class MobileViewHolder extends RecyclerView.ViewHolder {
        ImageView imageMobile;
        TextView txtMobileName, txtMobilePrice, txtRentPrice, tvStatus, tvRentedBy;
        Button buttonViewDetails;
        Button btRent;

        public MobileViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMobile = itemView.findViewById(R.id.imageMobile);
            txtMobileName = itemView.findViewById(R.id.txtMobileName);
            txtMobilePrice = itemView.findViewById(R.id.txtMobilePrice);
            txtRentPrice = itemView.findViewById(R.id.txtRentPrice);
            buttonViewDetails = itemView.findViewById(R.id.buttonViewDetails);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvRentedBy = itemView.findViewById(R.id.tvRentedBy);
            btRent = itemView.findViewById(R.id.btRent);
            buttonViewDetails.setOnClickListener(clickListener);
            btRent.setOnClickListener(clickListener);
        }
    }
}
