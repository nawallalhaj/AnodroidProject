package com.example.nawalproj.Auction.AuctionAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nawalproj.Auction.AuctionProduct;
import com.example.nawalproj.R;
import com.example.nawalproj.AuctionModel.ProductModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class WinnerListAdapter extends RecyclerView.Adapter<WinnerListAdapter.viewholder> {
    private Context mcontext;
    private List<AuctionProduct> mList;
    private String username;

    public WinnerListAdapter(Context mcontext, List<AuctionProduct> mList, String username) {
        this.mcontext = mcontext;
        this.mList = mList;
        this.username = username;
    }

    @NonNull
    @Override
    public WinnerListAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.layout_show_all_auction_products, parent, false);
        WinnerListAdapter.viewholder holder = new WinnerListAdapter.viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WinnerListAdapter.viewholder holder, int position) {
        ProductModel model = mList.get(position);
        Glide.with(mcontext).load(model.getFoodImage()).into(holder.cv_foodImage);
        holder.tv_foodName.setText(model.getFoodName());
        holder.tv_foodDesc.setText(model.getBidder());
        holder.tv_foodPrice.setText("Bid : $" + model.getBid());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class viewholder extends RecyclerView.ViewHolder {

        private CircleImageView cv_foodImage;
        private TextView tv_foodName;
        private TextView tv_foodDesc;
        private TextView tv_foodPrice;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            cv_foodImage = itemView.findViewById(R.id.cv_foodImage);
            tv_foodName = itemView.findViewById(R.id.tv_foodName);
            tv_foodDesc = itemView.findViewById(R.id.tv_foodDesc);
            tv_foodPrice = itemView.findViewById(R.id.tv_foodPrice);

        }
    }
}

