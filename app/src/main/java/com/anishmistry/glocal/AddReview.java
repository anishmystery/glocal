package com.anishmistry.glocal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddReview extends AppCompatActivity {

    TextView textViewShopName;
    Button buttonSubmitReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        textViewShopName = findViewById(R.id.textShopName);
        buttonSubmitReview = findViewById(R.id.btnSubmitReview);

        buttonSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Review Submitted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
