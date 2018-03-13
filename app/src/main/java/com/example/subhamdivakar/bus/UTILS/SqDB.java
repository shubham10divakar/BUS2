package com.example.subhamdivakar.bus.UTILS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.subhamdivakar.bus.Bean.Busowner;
import com.example.subhamdivakar.bus.Bean.UserInfo;

import java.util.HashMap;

//import static com.example.subhamdivakar.UTILS.Constant.userInfo;

/**
 * Created by Administrator on 7/13/2016.
 */
public class SqDB extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "bus.db";
    public static final int Version=1;

    public static final String busowner_TABLE_NAME = "bus_owner";
    public static final String USER_TABLE_NAME = "user_table";
    public static final String USER_INFO_TABLE = "user_info";
    public static final String busowner_INFO_TABLE_NAME = "bus_table";


    public static final String USER_TABLE_COLUMN_NAME = "name";
    public static final String USER_TABLE_COLUMN_PHONE = "phone";

    public static final String USER_INFO_TABLE_COLUMN_NAME = "name";
    public static final String USER_INFO_TABLE_COLUMN_PHONE = "phone";
    public static final String USER_INFO_TABLE_COLUMN_ADDRESS = "address";


    public static final String busowner_INFO_TABLE_COLUMN_name = "owner_name";
    public static final String busowner_INFO_TABLE_COLUMN_busnum = "bus_num";
    public static final String busowner_INFO_TABLE_COLUMN_PHONE = "phone";
    public static final String busowner_INFO_TABLE_COLUMN_ADDRESS = "address";

    public static final String busowner_TABLE_COLUMN_name = "owner_name";
    public static final String busowner_TABLE_COLUMN_busnum = "bus_num";
    public static final String busowner_TABLE_COLUMN_PHONE = "phone";

    private HashMap hp;

    public SqDB(Context context)
    {
        super(context, DATABASE_NAME , null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table USER_INFO_TABLE " +
                        "(name text primary key, phone text,address text)"
        );
        db.execSQL(
                "create table busowner_INFO_TABLE " +
                        "(owner_name text primary key, phone text,bus_num text,address text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                if(newVersion>oldVersion)
               {
                    db.execSQL("DROP TABLE IF EXISTS contacts");
                    onCreate(db);
                }
    }


    public boolean insertUserInfo  (String name, String mobile,  String address)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", mobile);

        contentValues.put("address", address);

      long no=  db.insert("USER_INFO_TABLE", null, contentValues);
        if(no>0)
        {
            //TOAST FOR ALREADY REGISTERED bcs IF no<0 VALUE NOT INSERTED
        }
        return true;
    }

    public UserInfo getUserInfo()
    {
        UserInfo userInfo=new UserInfo();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from USER_INFO_TABLE", null );
       if( res.moveToNext())
       {
         userInfo.user_name=res.getString(res.getColumnIndex(USER_INFO_TABLE_COLUMN_NAME)) ;

           userInfo.phone=res.getString(res.getColumnIndex(USER_INFO_TABLE_COLUMN_PHONE)) ;
           userInfo.address=res.getString(res.getColumnIndex(USER_INFO_TABLE_COLUMN_ADDRESS)) ;
           return  userInfo;
       }
        return null;

    }
    public boolean insertbusownerInfo  (String name, String mobile,  String busnum,String address)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("owner_name", name);//owner_name is the name as in field db.execSQL and same for all
        contentValues.put("phone", mobile);
        contentValues.put("bus_num", busnum);
        contentValues.put("address", address);

        long no=  db.insert("busowner_INFO_TABLE", null, contentValues);
        return true;
    }
    public Busowner getBusowner()
    {
        Busowner busowner=new Busowner();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from busowner_INFO_TABLE", null );
        if( res.moveToNext())
        {
            busowner.user_name=res.getString(res.getColumnIndex(busowner_INFO_TABLE_COLUMN_name)) ;
            busowner.phone=res.getString(res.getColumnIndex(busowner_INFO_TABLE_COLUMN_PHONE)) ;
            busowner.address=res.getString(res.getColumnIndex(busowner_INFO_TABLE_COLUMN_ADDRESS)) ;
            busowner.bus_num=res.getString(res.getColumnIndex(busowner_INFO_TABLE_COLUMN_busnum)) ;
            return  busowner;
        }
        return null;

    }

}
