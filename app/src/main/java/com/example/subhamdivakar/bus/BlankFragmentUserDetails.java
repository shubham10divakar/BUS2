package com.example.subhamdivakar.bus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.subhamdivakar.bus.UTILS.SqDB;

public class BlankFragmentUserDetails extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View obj=inflater.inflate(R.layout.fragment_blank_fragment_user_details, container, false);

        SqDB obj1=new SqDB(this.getActivity());
        String name = String.valueOf(obj1.getUserInfo().user_name);
        String phone = String.valueOf(obj1.getUserInfo().phone);
        String address = String.valueOf(obj1.getUserInfo().address);
       // String name ="Divakar";
        TextView messageView = (TextView)obj.findViewById(R.id.textView7);
        messageView.setText(name);

        TextView messageView2 = (TextView)obj.findViewById(R.id.textView9);
        messageView2.setText(phone);

        TextView messageView4 = (TextView)obj.findViewById(R.id.textView11);
        messageView4.setText(address);
          return obj;
    }

}

