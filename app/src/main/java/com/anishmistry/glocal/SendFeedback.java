package com.anishmistry.glocal;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SendFeedback extends AppCompatActivity {

    TextView textViewHelp;
    Button buttonSendFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);

        textViewHelp = findViewById(R.id.textHelp);
        buttonSendFeedback = findViewById(R.id.btnSubmitFeedback);

        SpannableString span = new SpannableString(textViewHelp.getText().toString());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse("email"));
                String[] s = {"help@glocal.com"};
                i.putExtra(Intent.EXTRA_EMAIL, s);
                i.putExtra(Intent.EXTRA_SUBJECT, "");
                i.putExtra(Intent.EXTRA_TEXT, "");
                i.setType("message/rfc822");
                Intent chooser = Intent.createChooser(i, "Launch Email");
                startActivity(chooser);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.BLUE);
            }
        };
        span.setSpan(clickableSpan, 80, 95, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewHelp.setText(span);
        textViewHelp.setMovementMethod(LinkMovementMethod.getInstance());

        buttonSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
