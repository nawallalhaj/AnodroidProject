package com.example.nawalproj.Classes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nawalproj.UserPages.PlaceBidActivity;
import com.example.nawalproj.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ProductAdapterAuction extends RecyclerView.Adapter<ProductAdapterAuction.ViewHolder> {
    List<AuctionProduct> productList;
    int aid;
    String uid;
    View view;
    Context context;

    public ProductAdapterAuction(Context context, List<AuctionProduct> productList) {
        this.context = context;
        this.productList = productList;
        Log.d("list size",""+productList.size());
    }

    @Override
    public ProductAdapterAuction.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        view = LayoutInflater.from(context).inflate(R.layout.each_bid, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ProductAdapterAuction.ViewHolder holder, int position) {

        // here we will find the position and start setting the output on our views

        String typeOfProduct = productList.get(position).getProdType();
        double price = productList.get(position).getMinprice();
        byte[] images = productList.get(position).getProdimg();
        Bitmap bm = BitmapFactory.decodeByteArray(images, 0 ,images.length);
        aid = productList.get(position).getAid();
        uid = FirebaseAuth.getInstance().getUid();
        holder.tvTypeOfProduct.setText(typeOfProduct);
        holder.tvJewelryPrice.setText(price+"");
        holder.imageOfProduct.setImageBitmap(bm);


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    // in order to make our views responsive we can implement onclicklistener on our recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // here we will find the views on which we will inflate our data

        TextView tvTypeOfProduct, tvJewelryPrice;
        ImageView imageOfProduct;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTypeOfProduct = itemView.findViewById(R.id.eachAuctionItemName);
            tvJewelryPrice = itemView.findViewById(R.id.eachAuctionItemPriceTv);
            imageOfProduct = itemView.findViewById(R.id.eachAuctionItemIV);

            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), PlaceBidActivity.class);
            intent.putExtra("id", productList.get(getLayoutPosition()).getAid() + "");
            v.getContext().startActivity(intent);
        }

    }
}
