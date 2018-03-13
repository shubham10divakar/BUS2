package com.example.subhamdivakar.bus;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.subhamdivakar.bus.Bean.Result;
import com.example.subhamdivakar.bus.BeanParameter.Complaints;
import com.example.subhamdivakar.bus.BeanParameter.Suggestions;
import com.example.subhamdivakar.bus.Listener.AsyncListener;
import com.example.subhamdivakar.bus.Parser.Parser;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Suggestion extends AppCompatActivity {
    public EditText txtprob,txtapp;
    Button btnReg = null;
    EditText txtcomp,txtbus_num;
//    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        txtprob = (EditText) findViewById(R.id.editText3);
        txtapp = (EditText) findViewById(R.id.editText2);
        btnReg = (Button) findViewById(R.id.button);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncListener<Suggestions> userRegAsyncListener = new AsyncListener<Suggestions>() {
                    Suggestions beanUserReg = new Suggestions();
                    private ProgressDialog pDialog;

                    @Override
                    public Suggestions onPreDownload() {

                        Suggestion.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pDialog = new ProgressDialog(Suggestion.this);
                                pDialog.setMessage("Getting Data ...");
                                pDialog.setIndeterminate(false);
                                pDialog.setCancelable(true);

                                pDialog.show();

                                beanUserReg.suggest = txtprob.getText().toString();
                                beanUserReg.app = txtapp.getText().toString();
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

                                Toast.makeText(Suggestion.this,"Done,Thanks",Toast.LENGTH_LONG).show();


                            } else {
                                Toast.makeText(Suggestion.this,"Try again",Toast.LENGTH_LONG).show();
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
