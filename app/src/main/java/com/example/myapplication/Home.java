package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class Home extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View  view =  inflater.inflate(R.layout.fragment_home, container, false);
       CardView c =(CardView)view .findViewById(R.id.chatdoctor1);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//             Intent intent = new Intent(getActivity(),MapsActivity2.class);
//             startActivity(intent);

            }
        });
//

        // Inflate the layout for this fragment

        return view;


    }

}