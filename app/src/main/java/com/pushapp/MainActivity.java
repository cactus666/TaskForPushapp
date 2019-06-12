package com.pushapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pushapp.POJO.Password;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.pushapp.DB.DBHelper.getInstance;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Password> passwords = new ArrayList<>();
    //RecyclerView.Adapter<PassAdapter.PasswordsHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase.loadLibs(this);

        getSupportActionBar().setTitle(R.string.title_first_act);

        passwords = getInstance(MainActivity.this).getAllPassword();
        mRecyclerView = findViewById(R.id.recycler_view_passwords);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        System.out.println("SIZE = "+passwords.size());
        mRecyclerView.setAdapter(new PassAdapter(passwords, this));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_for_new_record = new Intent(MainActivity.this, NewRecordActivity.class);
                startActivityForResult(intent_for_new_record, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 1){
            Password new_element = (Password) data.getParcelableExtra("new_obj");
            passwords.add(new_element);
            //adapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(new PassAdapter(passwords, this));
            getInstance(MainActivity.this).insertNewPass(new_element);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        passwords = getInstance(MainActivity.this).getAllPassword();
        //adapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(new PassAdapter(passwords, this));
    }
}
