package com.example.subhamdivakar.bus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.subhamdivakar.bus.Bean.Result;
import com.example.subhamdivakar.bus.Bean.UserInfo;
import com.example.subhamdivakar.bus.BeanParameter.UserReg;
import com.example.subhamdivakar.bus.BeanParameter.ZoneBean;
import com.example.subhamdivakar.bus.Listener.AsyncListener;
import com.example.subhamdivakar.bus.Parser.Parser;
import com.example.subhamdivakar.bus.UTILS.SqDB;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Main3Activity extends AppCompatActivity {
    public EditText name, addr, phn;
    Button btnReg = null;
    EditText txtname, txtcontact, txtaddress;
//    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        SqDB database = new SqDB(this);
        UserInfo obj = database.getUserInfo();
        if (obj != null) {
            Intent objIntent = new Intent(Main3Activity.this, User.class);
            startActivity(objIntent);
            finish();
        }
        else {
            txtname = (EditText) findViewById(R.id.name);
            txtcontact = (EditText) findViewById(R.id.PHONE);
            txtaddress = (EditText) findViewById(R.id.address);
            btnReg = (Button) findViewById(R.id.btnreg);
            final ZoneBean zoneBean = new ZoneBean();

            final SqDB database1 = new SqDB(this);

            btnReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AsyncListener<UserReg> userRegAsyncListener = new AsyncListener<UserReg>() {
                        UserReg beanUserReg = new UserReg();
                        private ProgressDialog pDialog;

                        @Override
                        public UserReg onPreDownload() {

                            Main3Activity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pDialog = new ProgressDialog(Main3Activity.this);
                                    pDialog.setMessage("Getting Data ...");
                                    pDialog.setIndeterminate(false);
                                    pDialog.setCancelable(true);

                                    pDialog.show();

                                    beanUserReg.name = txtname.getText().toString();
                                    beanUserReg.contact_no = txtcontact.getText().toString();
                                    beanUserReg.address = txtaddress.getText().toString();
                                }
                            });

                            return beanUserReg;
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
                                    //UserInfo obj=database.getUserInfo();
                                    txtname = (EditText) findViewById(R.id.name);
                                    txtcontact = (EditText) findViewById(R.id.PHONE);
                                    txtaddress = (EditText) findViewById(R.id.address);
                                    database1.insertUserInfo(txtname.getText().toString(), txtcontact.getText().toString(), txtaddress.getText().toString());
                                    Intent objIntent = new Intent(Main3Activity.this, User.class);
                                    startActivity(objIntent);
                                    finish();

                                } else {
                                    Snackbar snackbar = Snackbar.make(txtcontact, "This Phone No Is Allready Registrator...", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                }
                            } catch (Exception ex) {

                            }
                        }

                        @Override
                        public void onCancel() {

                        }
                    };

                    Parser<UserReg> zoneBeanParser = new Parser<UserReg>();

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


//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Regi_ Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://com.example.subhamdivakar/bus/http/host/path")
//        //android-app://com.example.subhamdivakar/http/host/path
//        );
//       // AppIndex.AppIndexApi.start(client, viewAction);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Regi_ Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://com.example.subhamdivakar/bus/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
//    }


    }

//    public void begin(View view) {
//        SqDB database = new SqDB(this);
//
//        name = (EditText) findViewById(R.id.name);
//        addr = (EditText) findViewById(R.id.address);
//        phn = (EditText) findViewById(R.id.PHONE);
//        database.insertUserInfo(name.getText().toString(), phn.getText().toString(), addr.getText().toString());
//
//        Intent objIntent = new Intent(Main3Activity.this, activity3.class);
//        startActivity(objIntent);
//    }
    //}




