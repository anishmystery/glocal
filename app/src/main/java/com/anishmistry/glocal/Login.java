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

    Button signin;
    EditText user, pwd;
    TextView signup, head;
    Boolean onboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
            onboard = extras.getBoolean("onboard");
        if(onboard==null)
            startActivity(new Intent(getApplicationContext(), OnBoarding.class));
        else
            setContentView(R.layout.activity_login);

        signup = (TextView)findViewById(R.id.createAccount);
        SpannableString span = new SpannableString(signup.getText().toString());
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
        signup.setText(span);
        signup.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
