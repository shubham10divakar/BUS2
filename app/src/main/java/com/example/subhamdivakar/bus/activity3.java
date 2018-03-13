package com.example.subhamdivakar.bus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.subhamdivakar.bus.Bean.Busowner;
import com.example.subhamdivakar.bus.Bean.UserInfo;
import com.example.subhamdivakar.bus.UTILS.SqDB;

public class activity3 extends AppCompatActivity {
    //public static final String EXTRA_MESSAGE = "message";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3);
//        Intent intent = getIntent();
//        String messageText = intent.getStringExtra(EXTRA_MESSAGE);
//        TextView messageView = (TextView)findViewById(R.id.message);
//        messageView.setText(messageText);

        SqDB database=new SqDB(this);
        Busowner obj1=database.getBusowner();
        UserInfo obj2=database.getUserInfo();
        if(obj1!=null) {
            String messageText = String.valueOf(database.getBusowner().user_name);
            TextView messageView = (TextView)findViewById(R.id.message);
            messageView.setText(messageText);

        }
        else if(obj2!=null)
        {
            String messageText = String.valueOf(database.getUserInfo().user_name);
            TextView messageView = (TextView)findViewById(R.id.message);
            messageView.setText(messageText);

        }



    }
   public void busschedule(View view)
   {
       //FragmentManager fm=activity3.this.getSupportFragmentManager();
       //int id=item.getItemId();
       //fm.beginTransaction().replace(R.id.container,new BlankFragment1()).commit();
       Toast.makeText(this,"called",Toast.LENGTH_LONG).show();
//       Intent obj=new Intent(this,activity5.class);
//       startActivity(obj);

   }
    public void bustiming(View view)
    {
        Toast.makeText(this,"called",Toast.LENGTH_LONG).show();
        Intent obj=new Intent(this,activity5.class);
        startActivity(obj);
    }
    public void busfare(View view)
    {
        Toast.makeText(this,"called",Toast.LENGTH_LONG).show();
        //Intent obj=new Intent(this,activity6.class);
        //startActivity(obj);
    }
    public void service(View view)
    {
        Toast.makeText(this,"called",Toast.LENGTH_LONG).show();
        //Intent obj=new Intent(this,activity6.class);
        //startActivity(obj);
    }
    public void busscheduleupload(View view)
    {
        //FragmentManager fm=activity3.this.getSupportFragmentManager();
        //int id=item.getItemId();
        //fm.beginTransaction().replace(R.id.container,new BlankFragment1()).commit();
        Toast.makeText(this,"called",Toast.LENGTH_LONG).show();
        Intent obj=new Intent(this,Main4Activity.class);
        startActivity(obj);

    }

}