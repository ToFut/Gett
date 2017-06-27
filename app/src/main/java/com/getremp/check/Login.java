package com.getremp.check;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Login extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
    }


    public void sendMessage(View view) {

        Intent intent = new Intent(this, DeafultPage.class);
        startActivity(intent);
    }
}