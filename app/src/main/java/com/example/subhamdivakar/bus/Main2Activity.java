package com.example.subhamdivakar.bus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.subhamdivakar.bus.Bean.Busowner;
import com.example.subhamdivakar.bus.Bean.Result;
import com.example.subhamdivakar.bus.BeanParameter.BusReg;
import com.example.subhamdivakar.bus.BeanParameter.UserReg;
import com.example.subhamdivakar.bus.Listener.AsyncListener;
import com.example.subhamdivakar.bus.Parser.Parser;
import com.example.subhamdivakar.bus.UTILS.SqDB;
import com.google.gson.Gson;

import org.json.JSONObject;

//import static com.example.subhamdivakar.bus.R.id.editText;

public class Main2Activity extends AppCompatActivity {
    //public EditText name,addr,phn,busnum;
    public TextView ans;


    public EditText name, addr, phn;
    Button btnReg = null;
    EditText txtname, txtcontact, txtaddress,txtbusnum;
//    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SqDB database = new SqDB(this);
        Busowner obj = database.getBusowner();
        if (obj != null) {
            level r=new level();
            r.levelbus=1;
            Intent objIntent = new Intent(Main2Activity.this, BusOwner.class);
            startActivity(objIntent);
            finish();
        } else {
            txtname = (EditText) findViewById(R.id.name);
            txtcontact = (EditText) findViewById(R.id.PHONE);
            txtaddress = (EditText) findViewById(R.id.address);
            txtbusnum=(EditText) findViewById(R.id.bus_num) ;
            btnReg = (Button) findViewById(R.id.btnreg);

            final SqDB database1 = new SqDB(this);

            btnReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AsyncListener<BusReg> userRegAsyncListener = new AsyncListener<BusReg>() {
                        BusReg beanBusReg = new BusReg();
                        private ProgressDialog pDialog;

                        @Override
                        public BusReg onPreDownload() {

                            Main2Activity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pDialog = new ProgressDialog(Main2Activity.this);
                                    pDialog.setMessage("Getting Data ...");
                                    pDialog.setIndeterminate(false);
                                    pDialog.setCancelable(true);

                                    pDialog.show();

                                    beanBusReg.name = txtname.getText().toString();
                                    beanBusReg.contact_no = txtcontact.getText().toString();
                                    beanBusReg.address = txtaddress.getText().toString();
                                    beanBusReg.bus_num=txtbusnum.getText().toString();
                                }
                            });

                            return beanBusReg;
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
                                    txtbusnum=(EditText) findViewById(R.id.bus_num);
                                    database1.insertbusownerInfo (txtname.getText().toString(), txtcontact.getText().toString(),txtbusnum.getText().toString(), txtaddress.getText().toString());
                                    level r=new level();
                                    r.levelbus=1;
                                    Intent objIntent = new Intent(Main2Activity.this, BusOwner.class);
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
}
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//        SqDB database=new SqDB(this);
//        Busowner obj=database.getBusowner();
//       if(obj!=null)
//        {
//            Intent objIntent=new Intent(Main2Activity.this,activity3.class);
//            startActivity(objIntent);
//
//        }
//
//    }
//    public void begin(View view)
//    {
//        SqDB database=new SqDB(this);
//
//        name=(EditText) findViewById(R.id.name);
//        addr=(EditText) findViewById(R.id.address);
//        phn=(EditText) findViewById(R.id.PHONE);
//        busnum=(EditText) findViewById(R.id.bus_num);
//
//        boolean result=database.insertbusownerInfo(name.getText().toString(),phn.getText().toString(),addr.getText().toString(),busnum.getText().toString());
//        if(result)
//        {
//            //Toast for registration is done
//        }
//        Intent objIntent=new Intent(Main2Activity.this,activity3.class);
//        startActivity(objIntent);
//    }
