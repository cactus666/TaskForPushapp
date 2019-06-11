package com.pushapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class NewRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);

       // Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        final EditText val_pass2_et = (EditText)findViewById(R.id.val_pass2);

        findViewById(R.id.pass2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (val_pass2_et.getTransformationMethod() == null)
                    val_pass2_et.setTransformationMethod(new PasswordTransformationMethod());
                else
                    val_pass2_et.setTransformationMethod(null);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
