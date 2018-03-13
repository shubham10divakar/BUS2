package com.example.subhamdivakar.bus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.subhamdivakar.bus.Bean.Busowner;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            }
            public void start(View view)
            {
                level r=new level();
                r.levelbus=0;
                Intent obj=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(obj);
            }
            public void start2(View view)
            {
                Intent obj=new Intent(MainActivity.this,Main3Activity.class);
                startActivity(obj);
            }
            public void start3(View view)
            {
                Intent obj=new Intent(MainActivity.this,Guest.class);
                startActivity(obj);
            }
    }
