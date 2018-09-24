package com.anishmistry.glocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SellerDashboard extends AppCompatActivity {

    private TextView textViewWelcomeUser;
    private String welcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);

        textViewWelcomeUser = (TextView)findViewById(R.id.welcomeMessage);
        welcomeMessage = getIntent().getStringExtra("sellerName");
        textViewWelcomeUser.setText("Welcome "+welcomeMessage+"!");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }
}
