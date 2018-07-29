package com.anishmistry.glocal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    Button buttonLogin;
    EditText editTextPhone;
    TextView textViewSignup;
    Boolean fromOnboarding;
    String spanString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
            fromOnboarding = extras.getBoolean("fromOnboarding");
        if(fromOnboarding ==null)
            startActivity(new Intent(getApplicationContext(), OnBoarding.class));

        editTextPhone = findViewById(R.id.username);
        buttonLogin = findViewById(R.id.login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextPhone != null) {
                    if(editTextPhone.length()!=10) {
                        editTextPhone.setError("Invalid phone number");
                    }
                }
                else {
                    editTextPhone.setError("Phone number required");
                }
            }
        });

        textViewSignup = (TextView)findViewById(R.id.createAccount);
        SpannableString span = new SpannableString("Don't have an account? Sign up now!");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Signup.class));
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.BLUE);
            }
        };
        span.setSpan(clickableSpan, 23,35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewSignup.setText(span);
        textViewSignup.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
