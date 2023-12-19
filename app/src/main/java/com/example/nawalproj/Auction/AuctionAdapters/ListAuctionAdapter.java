package com.example.nawalproj.Auction.AuctionAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nawalproj.Auction.AuctionProduct;
import com.example.nawalproj.R;

public class ListAuctionAdapter extends BaseAdapter {
    Context context;
    AuctionProduct[] productlist;
    LayoutInflater layoutInflater;
    public ListAuctionAdapter(Context context,AuctionProduct[] productlist){
        this.context = context;
        this.productlist = productlist;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return productlist.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.activity_list_view,null);
        TextView textView = view.findViewById(R.id.productTV);
        ImageView image = view.findViewById(R.id.imImage);
        textView.setText(productlist[i].toString());
        byte[] im = productlist[i].getProdimg();
        Bitmap bm = BitmapFactory.decodeByteArray(im, 0 ,im.length);
        image.setImageBitmap(bm);
        return view;
    }
}
