package com.example.nawalproj.UserPages;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.*;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nawalproj.Classes.Product;
import com.example.nawalproj.Classes.ProductAdapter;
import com.example.nawalproj.DataBase.DBHelper;


import java.util.ArrayList;
import java.util.List;

import com.example.nawalproj.R;

/**
 * A simple {@link Fragment} subclass
 * create an instance of this fragment.
 */
public class home extends Fragment implements View.OnClickListener {

    ImageView ringimage, braceletimage, earringimage, necklaceimage, trendeyimage, lastpiecesimage, auctionimage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
               ringimage = v.findViewById(R.id.ringImage);
               ringimage.setOnClickListener(this);
               braceletimage = v.findViewById(R.id.braceletImage);
               braceletimage.setOnClickListener(this);
               earringimage = v.findViewById(R.id.earringImage);
               earringimage.setOnClickListener(this);
               necklaceimage = v.findViewById(R.id.necklaceImage);
               necklaceimage.setOnClickListener(this);
               trendeyimage = v.findViewById(R.id.fireImage);
               trendeyimage.setOnClickListener(this);
               lastpiecesimage = v.findViewById(R.id.lastImage);
               lastpiecesimage.setOnClickListener(this);
               auctionimage = v.findViewById(R.id.auctionImage);
               auctionimage.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() ==  R.id.ringImage){
             Intent i = new Intent(getContext(), ProductView.class);
             i.putExtra("Category","Ring");
             startActivity(i);
        }
        if(v.getId() ==  R.id.braceletImage){
            Intent i = new Intent(getContext(), ProductView.class);
            i.putExtra("Category","Bracelet");
            startActivity(i);
        }
        if(v.getId() ==  R.id.earringImage){
            Intent i = new Intent(getContext(), ProductView.class);
            i.putExtra("Category","Earring");
            startActivity(i);
        }
        if(v.getId() ==  R.id.necklaceImage){
            Intent i = new Intent(getContext(), ProductView.class);
            i.putExtra("Category","Necklace");
            startActivity(i);
        }
        if(v.getId() ==  R.id.fireImage){

        }
        if(v.getId() ==  R.id.lastImage){
            Intent i = new Intent(getContext(), ProductView.class);
            i.putExtra("Category","LastPieces");
            startActivity(i);
        }

    }
}