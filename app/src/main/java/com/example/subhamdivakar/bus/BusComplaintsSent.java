package com.example.subhamdivakar.bus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.subhamdivakar.bus.Bean.Result;
import com.example.subhamdivakar.bus.Bean.UserInfo;
import com.example.subhamdivakar.bus.BeanParameter.Complaints;
import com.example.subhamdivakar.bus.BeanParameter.UserReg;
import com.example.subhamdivakar.bus.BeanParameter.ZoneBean;
import com.example.subhamdivakar.bus.Listener.AsyncListener;
import com.example.subhamdivakar.bus.Parser.Parser;
import com.example.subhamdivakar.bus.UTILS.SqDB;
import com.google.gson.Gson;

import org.json.JSONObject;

public class BusComplaintsSent extends AppCompatActivity {
    public EditText name, addr, phn;
    Button btnReg = null;
    EditText txtcomp,txtbus_num;
//    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_complaints_sent);
            txtcomp = (EditText) findViewById(R.id.editText3);
            txtbus_num = (EditText) findViewById(R.id.editText2);
            btnReg = (Button) findViewById(R.id.button);

            btnReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AsyncListener<Complaints> userRegAsyncListener = new AsyncListener<Complaints>() {
                        Complaints beanUserReg = new Complaints();
                        private ProgressDialog pDialog;

                        @Override
                        public Complaints onPreDownload() {

                            BusComplaintsSent.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pDialog = new ProgressDialog(BusComplaintsSent.this);
                                    pDialog.setMessage("Getting Data ...");
                                    pDialog.setIndeterminate(false);
                                    pDialog.setCancelable(true);

                                    pDialog.show();

                                    beanUserReg.complaint = txtcomp.getText().toString();
                                    beanUserReg.bus_num = txtbus_num.getText().toString();
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

                                    Toast.makeText(BusComplaintsSent.this,"Complaint Registered",Toast.LENGTH_LONG).show();


                                } else {
                                    Toast.makeText(BusComplaintsSent.this,"Try again",Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception ex) {

                            }
                        }

                        @Override
                        public void onCancel() {

                        }
                    };

                    Parser<BusComplaintsSent> zoneBeanParser = new Parser<BusComplaintsSent>();

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
