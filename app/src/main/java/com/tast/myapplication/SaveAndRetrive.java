package com.tast.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SaveAndRetrive extends AppCompatActivity {

     ArrayList<EmailNums> arrPackage;
    EditText email_input,number_input;
    Button submit_btn;
    SharedPreferences sharedpreferences;
    RecyclerView recycler_view;
    LinearLayoutManager llm;
    ArrayList<String> emailChecker;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_and_retrive);

        sharedpreferences = getSharedPreferences("USER",MODE_PRIVATE);
        arrPackage = new ArrayList<>();
        emailChecker = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        recycler_view = findViewById(R.id.recycler_view);
        submit_btn = findViewById(R.id.submit_btn);
        email_input = findViewById(R.id.email_input);
        number_input = findViewById(R.id.number_input);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performData();
            }
        });

        Gson gson = new Gson();
        String json = gson.toJson(arrPackage);
        //arrPackage.add(new EmailNums("",""));
        String json1 = sharedpreferences.getString("Set", "");
        if (json1.isEmpty()) {
        } else {
            Type type = new TypeToken<List<EmailNums>>() {
            }.getType();
            ArrayList<EmailNums> arrPackageData = gson.fromJson(json1, type);

            for (int i=0;i<=arrPackageData.size()-1;i++){
                emailChecker.add(arrPackageData.get(i).getEmsilId());

                arrPackage.add(new EmailNums(arrPackageData.get(i).getEmsilId(),arrPackageData.get(i).getNumber()));
            }
            DisplayAdapter disp = new DisplayAdapter(arrPackageData,this);
            recycler_view.setLayoutManager(llm);
            recycler_view.setAdapter(disp);

        }
    }

    private void performData(){

        if (email_input.getText().toString().trim().isEmpty()){
            email_input.setError("Enter Email Id");
            email_input.requestFocus();
        }else if (!email_input.getText().toString().matches(emailPattern)){
            email_input.setError("Enter Valid Email Id");
            email_input.requestFocus();
        }else if (number_input.getText().toString().trim().isEmpty()){
            number_input.setError("Enter Email Id");
            number_input.requestFocus();
        }else {
            //perform submit operation

            String nameData = email_input.getText().toString().trim();
            String addressData = number_input.getText().toString().trim();
            if (emailChecker.size()!=0){
                if (emailChecker.contains(""+email_input.getText().toString().trim())){
                    email_input.setError("Enter Valid Email Id");
                    email_input.requestFocus();
                }else {
                    arrPackage.add(new EmailNums(nameData,addressData));
                    //arrPackage.add(addressData);
                    Gson gson = new Gson();
                    String json = gson.toJson(arrPackage);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("Set",json );
                    editor.commit();




                    
                    String json1 = sharedpreferences.getString("Set", "");
                    if (json1.isEmpty()) {
                    } else {
                        Type type = new TypeToken<List<EmailNums>>() {
                        }.getType();
                        ArrayList<EmailNums> arrPackageData = gson.fromJson(json1, type);
                        for (int i=0;i<=arrPackageData.size()-1;i++){
                            emailChecker.add(arrPackageData.get(i).getEmsilId());
                        }
                        DisplayAdapter disp = new DisplayAdapter(arrPackageData,this);
                        recycler_view.setLayoutManager(llm);
                        recycler_view.setAdapter(disp);
                    }
                }
            }else {

                arrPackage.add(new EmailNums(nameData,addressData));
                //arrPackage.add(addressData);
                Gson gson = new Gson();
                String json = gson.toJson(arrPackage);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Set",json );
                editor.commit();


                String json1 = sharedpreferences.getString("Set", "");
                if (json1.isEmpty()) {
                } else {
                    Type type = new TypeToken<List<EmailNums>>() {
                    }.getType();
                    ArrayList<EmailNums> arrPackageData = gson.fromJson(json1, type);
                    for (int i=0;i<=arrPackageData.size()-1;i++){
                        emailChecker.add(arrPackageData.get(i).getEmsilId());
                    }
                    DisplayAdapter disp = new DisplayAdapter(arrPackageData,this);
                    recycler_view.setLayoutManager(llm);
                    recycler_view.setAdapter(disp);
                }
            }



        }
    }
}