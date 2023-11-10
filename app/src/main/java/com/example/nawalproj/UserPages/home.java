package com.example.nawalproj.UserPages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nawalproj.R;

/**
 * A simple {@link Fragment} subclass
 * create an instance of this fragment.
 */
public class home extends Fragment implements View.OnClickListener {

    ImageView ringimage, braceletimage, earringimage, necklaceimage, trendeyimage, lastpiecesimage;
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
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() ==  R.id.ringImage){

        }
        if(v.getId() ==  R.id.braceletImage){

        }
        if(v.getId() ==  R.id.earringImage){

        }
        if(v.getId() ==  R.id.necklaceImage){

        }
        if(v.getId() ==  R.id.fireImage){

        }
        if(v.getId() ==  R.id.lastImage){

        }

    }
}