package com.pushapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.pushapp.DB.DBHelper;
import com.pushapp.POJO.Password;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;

public class PassAdapter extends RecyclerView.Adapter<PassAdapter.PasswordsHolder> {
    private List<Password> mData = new ArrayList<>();
    private String[] tools = {"", "Скопировать пароль", "Редактировать", "Удалить"};
    private Context context;

    public PassAdapter(List<Password> passwords, Context context) {
        mData.addAll(passwords);
        this.context = context;
    }

    @NonNull
    @Override
    public PasswordsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.list_item_passwords, parent, false);

        Spinner spinner = (Spinner)view.findViewById(R.id.settings);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, tools);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return new PasswordsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordsHolder holder, int position) {
        holder.bind(mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class PasswordsHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView link;
        private Password password_el;
        private RecyclerView mRecyclerView;
        private int position_password_item;

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                if(password_el != null)
                    switch(position){
                        case 1:
                            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("copy password", password_el.getPass());
                            clipboardManager.setPrimaryClip(clip);
                            break;
                        case 2:
                            Intent intent_for_new_record = new Intent("com.pushapp.add_new_password");
                            intent_for_new_record.putExtra("edit_element", password_el);
                            context.startActivity(intent_for_new_record);
                            break;
                        case 3:
                            DBHelper.getInstance(context).deletePass(password_el.getId());
                            mRecyclerView = ((MainActivity)context).findViewById(R.id.recycler_view_passwords);
                            mData.remove(position_password_item);
                            mRecyclerView.setAdapter(new PassAdapter(mData, context));
                            break;
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };

        PasswordsHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name_in_item);
            link = view.findViewById(R.id.link_in_item);

            ((Spinner)view.findViewById(R.id.settings)).setOnItemSelectedListener(itemSelectedListener);

        }

        void bind(Password password, int position) {
            password_el = password;
            name.setText(password.getName());
            link.setText(password.getLink());
            this.position_password_item = position;
        }

    }

}