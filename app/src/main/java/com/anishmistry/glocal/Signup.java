package com.anishmistry.glocal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    private Button signupBtn;
    private EditText name, phone, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText)findViewById(R.id.sellerName);
        phone = (EditText)findViewById(R.id.sellerPhone);
        phone.setText("+91");
        email = (EditText)findViewById(R.id.sellerEmail);
        signupBtn = (Button)findViewById(R.id.signup);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OTPAuth.class);
                intent.putExtra("phone", phone.getText().toString().trim());
                startActivity(intent);
            }
        });
    }
}
