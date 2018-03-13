package com.example.subhamdivakar.bus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.subhamdivakar.bus.R;
import com.example.subhamdivakar.bus.UTILS.SqDB;


public class BlankFragmentGuest extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containerguest,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View obj=inflater.inflate(R.layout.fragment_blank_fragment_guest,containerguest, false);
        return obj;
    }

}