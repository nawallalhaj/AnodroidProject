package com.example.nawalproj.Auction.AuctionAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nawalproj.Auction.AuctionBidders;
import com.example.nawalproj.Auction.AuctionProduct;
import com.example.nawalproj.Auction.UserBidder.PlaceBidActivity;
import com.example.nawalproj.R;
import com.example.nawalproj.MainPages.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowAllBiddersAdapter extends RecyclerView.Adapter<ShowAllBiddersAdapter.ViewHolder> {

    List<AuctionBidders> bidderList;
    int pid;
    String uid;
    View view;
    Context context;

    public ShowAllBiddersAdapter(Context context, List<AuctionBidders> bidderList) {
        this.context = context;
        this.bidderList = bidderList;
    }

    @Override
    public ShowAllBiddersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        view = LayoutInflater.from(context).inflate(R.layout.each_bidder, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ShowAllBiddersAdapter.ViewHolder holder, int position) {

        // here we will find the position and start setting the output on our views


        String typeOfProduct = bidderList.get(position).getProdType();
        String bidderName = bidderList.get(position).getBidderName();
        double price = bidderList.get(position).getPrice();
        byte[] images = bidderList.get(position).getProdimg();
        Bitmap bm = BitmapFactory.decodeByteArray(images, 0 ,images.length);
        pid = bidderList.get(position).getPid();
        uid = FirebaseAuth.getInstance().getUid();
        holder.tvBidderName.setText(bidderName);
        holder.tvTypeOfProduct.setText(typeOfProduct);
        holder.tvJewelryPrice.setText(price+"");
        holder.imageOfProduct.setImageBitmap(bm);


    }

    @Override
    public int getItemCount() {
        return 0;
    }


    // in order to make our views responsive we can implement onclicklistener on our recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder {

        // here we will find the views on which we will inflate our data

        TextView tvTypeOfProduct, tvJewelryPrice, tvBidderName;
        ImageView imageOfProduct;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTypeOfProduct = itemView.findViewById(R.id.eachBidderJewelryType);
            tvJewelryPrice = itemView.findViewById(R.id.tv_bidPrice);
            tvBidderName= itemView.findViewById(R.id.tv_bidderName);
            imageOfProduct = itemView.findViewById(R.id.eachBidderIV);


        }

    }
}

