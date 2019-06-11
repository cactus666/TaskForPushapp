package com.pushapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import static com.pushapp.DB.DBHelper.getInstance;

public class NewRecordActivity extends AppCompatActivity {

    private Button btn_save;
    private EditText name;
    private EditText link;
    private EditText login;
    private EditText val_pass;
    private EditText val_pass2;
    private ImageButton icon_pass;
    private ImageButton icon_pass2;
    private ImageView arrow_back;

    View.OnClickListener listener_for_pass_icon = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == icon_pass2.getId())
                if (val_pass2.getTransformationMethod() == null)
                    val_pass2.setTransformationMethod(new PasswordTransformationMethod());
                else
                    val_pass2.setTransformationMethod(null);
            else
                if (val_pass.getTransformationMethod() == null)
                    val_pass.setTransformationMethod(new PasswordTransformationMethod());
                else
                    val_pass.setTransformationMethod(null);
        }
    };

    View.OnClickListener listener_save_btn = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if( (val_pass.getText().toString().intern() == val_pass2.getText().toString().intern() ) && (val_pass.getText().toString().trim().length() != 0) ){
                // еще проверка на наличие пароля
                getInstance(NewRecordActivity.this).insertNewPass(val_pass.getText().toString());
            }else{
                Toast.makeText(NewRecordActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
        }
    };

    View.OnClickListener listener_arrow_back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);

        btn_save = (Button)findViewById(R.id.save_btn);
        name = (EditText)findViewById(R.id.name);
        link = (EditText)findViewById(R.id.link);
        login = (EditText)findViewById(R.id.login);
        val_pass = (EditText)findViewById(R.id.val_pass);
        val_pass2 = (EditText)findViewById(R.id.val_pass2);
        icon_pass = findViewById(R.id.pass);
        icon_pass2 = findViewById(R.id.pass2);
        arrow_back = findViewById(R.id.back_arrow);

        icon_pass.setOnClickListener(listener_for_pass_icon);
        icon_pass2.setOnClickListener(listener_for_pass_icon);
        btn_save.setOnClickListener(listener_save_btn);
        arrow_back.setOnClickListener(listener_arrow_back);

         Toolbar toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
