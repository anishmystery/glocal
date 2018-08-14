package com.anishmistry.glocal;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;

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
        if (extras != null)
            fromOnboarding = extras.getBoolean("fromOnboarding");
        if (fromOnboarding == null)
            startActivity(new Intent(getApplicationContext(), OnBoarding.class));

        editTextPhone = findViewById(R.id.username);
        buttonLogin = findViewById(R.id.login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPhone != null) {
                    if (editTextPhone.length() != 10) {
                        editTextPhone.setError("Invalid phone number");
                    }
                } else {
                    editTextPhone.setError("Phone number required");
                }
            }
        });

        final MaterialStyledDialog.Builder dialogHeader = new MaterialStyledDialog.Builder(Login.this)
//                .setIcon(new IconicsDrawable(getApplicationContext()).icon(MaterialDesignIconic.Icon.gmi_google_play).color(Color.WHITE))
                .withDialogAnimation(true)
                .setTitle("Sign up as")
//                .setDescription("Glad to see you like MaterialStyledDialogs! If you're up for it, we would really appreciate you reviewing us.")
                .setStyle(Style.HEADER_WITH_TITLE)
                .setHeaderColor(R.color.colorDialog)
                .withDarkerOverlay(true)
                .setPositiveText("Seller")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        startActivity(new Intent(getApplicationContext(), Signup.class));
                    }
                })
                .setNegativeText("Buyer");

        textViewSignup = (TextView) findViewById(R.id.createAccount);
        SpannableString span = new SpannableString("Don't have an account? Sign up now!");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                dialogHeader.show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.BLUE);
            }
        };
        span.setSpan(clickableSpan, 23, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewSignup.setText(span);
        textViewSignup.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
