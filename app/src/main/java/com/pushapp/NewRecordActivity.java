package com.pushapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.pushapp.DB.DBHelper;
import com.pushapp.POJO.Password;

public class NewRecordActivity extends AppCompatActivity {

    private boolean state = false; // true - редактирование
    private Button btn_save;
    private Password updatePasswordElement;
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
                if(!state) {
                    // еще проверка на наличие пароля
                    Intent intent = new Intent();
                    intent.putExtra("new_obj", new Password(name.getText().toString(),
                            link.getText().toString(),
                            login.getText().toString(),
                            val_pass.getText().toString(),
                            DBHelper.getId()));
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    DBHelper.getInstance(NewRecordActivity.this).updatePass(updatePasswordElement);
                    System.out.println("finish _ new activity");
                    finish();
                }
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

         try {
             if (getIntent().getExtras() != null) {
                 state = true;
                 updatePasswordElement = (Password) getIntent().getExtras().getParcelable("edit_element");
                 name.setText(updatePasswordElement.getName());
                 link.setText(updatePasswordElement.getLink());
                 login.setText(updatePasswordElement.getLogin());
                 val_pass.setText(updatePasswordElement.getPass());
                 val_pass2.setText(updatePasswordElement.getPass());
             }
         }catch(Exception ex){
             Log.e("error", "забыл закинуть объект!(");
         }

    }

}
