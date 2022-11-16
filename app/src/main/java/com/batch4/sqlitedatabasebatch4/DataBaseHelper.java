package com.batch4.sqlitedatabasebatch4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase database;

    private static final String DATABASE_NAME = "codewall.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "_student";




    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table codewall.db (id Integer primary key autoincrement,
        //name Text,Email Text,Mobile Text);
        String query = "CREATE TABLE "+ TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, email TEXT," +
                "mobile TEXT);";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+TABLE_NAME);
        onCreate(db);
    }


    public long saveData(String id,String nameText,String emailText, String mobileText){
            database = this.getWritableDatabase();
        ContentValues obj =new ContentValues();
        obj.put("id",id);
        obj.put("name",nameText);
        obj.put("email",emailText);
        obj.put("mobile",mobileText);
        return database.insert(TABLE_NAME,null,obj);
    }

    public String getData(){
        database = this.getWritableDatabase();
        String[] columns = {"id","name","email","mobile"};
        Cursor cursor = database.query(TABLE_NAME,columns,null,null,null,null,null);

        int id = cursor.getColumnIndex("id");
        int name = cursor.getColumnIndex("name");
        int email = cursor.getColumnIndex("email");
        int mobile = cursor.getColumnIndex("mobile");

        String result = "";
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            result = result +
                    "Id: "+ cursor.getString(id)+"\n" +
                    "Name: "+ cursor.getString(name)+"\n"+
                    "Email: "+ cursor.getString(email)+"\n"+
                    "Mobile: "+ cursor.getString(mobile)+"\n \n";
        }

        return  result;
    }

    public void deleteData(long id) {
        database = this.getWritableDatabase();
        database.delete(TABLE_NAME,"id ="+id,null);
    }

    public void updateData(String id, String name, String email, String mobile) {
//        long id1 = Long.parseLong(id);
        database = this.getWritableDatabase();

        String [] names = name.split(" ");

        String str3="";
        for (String s : names) {
//            System.out.println(s.substring(0, 1).toUpperCase());
            String str =   s.substring(0,1).toUpperCase();
            String str1 = s.substring(1);
            String str2 = str+str1+" ";
            str3 += str2;
        }

        System.out.println(str3);
        
//        String[] name1 = name.split(" ");
//        //{tun,tun}
//        for (String s : name1) {
//
//        }
        //List list = new List();
        //HashMap map = new HashMap();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",str3);
        contentValues.put("email",email);
        contentValues.put("mobile",mobile);

        database.update(TABLE_NAME,contentValues,"id =?",new String[]{id});
//        database.close();
    }
}
