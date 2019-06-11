package com.pushapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.sqlcipher.database.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase.loadLibs(this);


        getSupportActionBar().setTitle(R.string.title_first_act);




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_for_new_record = new Intent(MainActivity.this, NewRecordActivity.class);
                startActivity(intent_for_new_record);
//                startActivityForResult();
            }
        });
    }




}
