package com.example.subhamdivakar.bus;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.subhamdivakar.bus.Bean.Result;
import com.example.subhamdivakar.bus.BeanParameter.Downloadimage;
import com.example.subhamdivakar.bus.Listener.AsyncListener;
import com.example.subhamdivakar.bus.Parser.Parser;
import com.example.subhamdivakar.bus.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class BusFare extends AppCompatActivity {
    //public EditText name,addr,phn,busnum;
    public TextView ans;


    public EditText id;
    Button btnReg = null;
//    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity5);
        //SqDB database = new SqDB(this);
        //Busowner obj = database.getBusowner();
        //SqDB database = new SqDB(this);
        //Busowner obj = database.getBusowner();
//        if (obj != null) {
//            Intent objIntent = new Intent(activity5.this, activity3.class);
//            startActivity(objIntent);
//
//        } else {
        id=(EditText) findViewById(R.id.editTextId) ;
        btnReg = (Button) findViewById(R.id.buttonGetImage);

        //final SqDB database1 = new SqDB(this);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncListener<Downloadimage> userRegAsyncListener = new AsyncListener<Downloadimage>() {
                    Downloadimage obj = new Downloadimage();
                    private ProgressDialog pDialog;

                    @Override
                    public Downloadimage onPreDownload() {

                        BusFare.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pDialog = new ProgressDialog(BusFare.this);
                                pDialog.setMessage("Getting Data ...");
                                pDialog.setIndeterminate(false);
                                pDialog.setCancelable(true);

                                pDialog.show();
                                obj.id=id.getText().toString();
                            }
                        });

                        return obj;
                    }

                    @Override
                    public void onPostDownload(JSONObject result) {
                        pDialog.dismiss();
                        try {
                            Gson gson = new Gson();
                            JSONObject object = result.getJSONObject("Result");
                            // JSONArray objzone = result.getJSONArray("Zone");
                            final Result finalresult = gson.fromJson(object.toString(), Result.class);
                            if (finalresult.status == 0) {
                                JSONArray arr = result.getJSONArray("Data");
                                JSONObject jsonObject=arr.getJSONObject(0);
                                String urldata=jsonObject.get("url").toString();//.get("url");

                                // Loader image - will be shown before loading image
                                int loader = R.drawable.hello;

                                // Imageview to show
                                ImageView image = (ImageView) findViewById(R.id.image);

                                // Image url
                                String image_url = urldata;

                                // ImageLoader class instance
                                ImageLoader imgLoader = new ImageLoader(getApplicationContext());
                                // whenever you want to load an image from url
                                // call DisplayImage function
                                // url - image url to load
                                // loader - loader image, will be displayed before getting image
                                // image - ImageView
                                imgLoader.DisplayImage(image_url, loader, image);



                            } else {
                                Snackbar snackbar = Snackbar.make(id, "This image is already there...", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        } catch (Exception ex) {

                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                };

                Parser<Downloadimage> zoneBeanParser = new Parser<Downloadimage>();

                zoneBeanParser.setListener(userRegAsyncListener);
                zoneBeanParser.execute();


                //  Toast.makeText(Regi_Activity.this,spnZone.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        // client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
}
//}

