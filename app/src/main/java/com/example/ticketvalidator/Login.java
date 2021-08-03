package com.example.ticketvalidator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    EditText phone,password;
    ImageView icon;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        icon = findViewById(R.id.icon);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                //Create the intent to start another activity
//                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                startActivity(intent);
            }
        });

}}