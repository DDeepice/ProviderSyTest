package com.example.hp.providersytest;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button query_all;

    private Button query_item;

    private EditText editText;

    String new_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        query_all = findViewById(R.id.query_all);
        query_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.hp.contentProviderSy/Contacts");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if(cursor!=null){
                    while(cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        int number = cursor.getInt(cursor.getColumnIndex("number"));
                        String gender = cursor.getString(cursor.getColumnIndex("gender"));

                        Log.d("ProviderTest:","姓名:"+name+"，联系方式:"+number+",性别："+gender);
                    }
                }
                cursor.close();
            }
        });

        editText = findViewById(R.id.new_id);
        new_id = editText.getText().toString();

        query_item = findViewById(R.id.query_item);
        query_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.hp.contentProviderSy/Contacts/");
                Cursor cursor = getContentResolver().query(uri,null,"id=?",new String[]{new_id},null);
                if(cursor!=null){
                    while(cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        int number = cursor.getInt(cursor.getColumnIndex("number"));
                        String gender = cursor.getString(cursor.getColumnIndex("gender"));

                        Log.d("ProviderTest:","姓名:"+name+"，联系方式:"+number+",性别："+gender);
                    }
                }
                cursor.close();
            }
        });
    }


}
