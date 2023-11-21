package com.example.nawalproj.Classes;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nawalproj.R;

import com.example.nawalproj.UserPages.info;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Product> productList;
    Context context;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.each_jewelry, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        // here we will find the position and start setting the output on our views

        String typeOfProduct = productList.get(position).getProdType();
        int yop = productList.get(position).getProdYOP();
        double price = productList.get(position).getSalesprice();
        byte[] images = productList.get(position).getProdimg();
        Bitmap bm = BitmapFactory.decodeByteArray(images, 0 ,images.length);

        holder.tvTypeOfProduct.setText(typeOfProduct);
        holder.tvJewelryPrice.setText((int) price);
        holder.tvJewelryYOP.setText(yop);
        holder.imageOfProduct.setImageBitmap(bm);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // in order to make our views responsive we can implement onclicklistener on our recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // here we will find the views on which we will inflate our data

        TextView tvTypeOfProduct, tvJewelryPrice, tvJewelryYOP;
        ImageView imageOfProduct;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTypeOfProduct = itemView.findViewById(R.id.eachJewelryYOPTv);
            tvJewelryPrice = itemView.findViewById(R.id.eachJewelryPriceTv);
            tvJewelryYOP = itemView.findViewById(R.id.eachJewelryYOPTv);
            imageOfProduct = itemView.findViewById(R.id.eachJewelryIv);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(v.getContext(),info.class);
            intent.putExtra("id",productList.get(getLayoutPosition()).getPid()+"");
            v.getContext().startActivity(intent);
        }
    }
}
