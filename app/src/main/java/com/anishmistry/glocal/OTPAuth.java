package com.anishmistry.glocal;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class OTPAuth extends AppCompatActivity {

    private EditText editTextMobile, editTextOTP;
    private TextView resendOTP;
    private Button buttonSendOTP, buttonVerifyOTP;
    private PinEntryEditText enterOTP;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    private String contactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpauth);

        Bundle extras = getIntent().getExtras();
        editTextMobile = findViewById(R.id.phone);
        buttonSendOTP = findViewById(R.id.sendOTP);
        resendOTP = findViewById(R.id.resendOTP);
        buttonVerifyOTP = findViewById(R.id.verifyOTP);
        enterOTP = findViewById(R.id.inputOTP);

        SpannableString span = new SpannableString("Didn't receive the code? Resend OTP.");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                resendVerificationCode(editTextMobile.getText().toString(), mResendToken);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.BLUE);
            }
        };
        span.setSpan(clickableSpan, 25,36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        resendOTP.setText(span);
        resendOTP.setMovementMethod(LinkMovementMethod.getInstance());

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
//        contactNumber = extras.getString("phone");
//        editTextMobile.setText(contactNumber);

        buttonSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSendOTP.setVisibility(View.INVISIBLE);
                buttonSendOTP.setEnabled(false);
                buttonVerifyOTP.setEnabled(true);
                buttonVerifyOTP.setVisibility(View.VISIBLE);
                editTextMobile.setVisibility(View.INVISIBLE);
                editTextMobile.setEnabled(false);
                resendOTP.setVisibility(View.VISIBLE);
                resendOTP.setEnabled(true);
                enterOTP.setVisibility(View.VISIBLE);
                enterOTP.setEnabled(true);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(editTextMobile.getText().toString().trim(),
                        60,
                        TimeUnit.SECONDS,
                        OTPAuth.this,
                        mCallbacks);
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.d("OTP", e.getMessage());
                editTextMobile.setError("Invalid number");
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };

        buttonVerifyOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enterOTP != null) {
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(mVerificationId, enterOTP.getText().toString());
                    signInWithPhoneAuthCredential(phoneAuthCredential);
                }
            }
        });
    }

    private void resendVerificationCode(String phoneNumber, PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(editTextMobile.getText().toString().trim(),
                60,
                TimeUnit.SECONDS,
                OTPAuth.this,
                mCallbacks,
                token);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(OTPAuth.this, "OTP verified", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(OTPAuth.this, "Invalid OTP Entered", Toast.LENGTH_SHORT).show();
                    if(task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                    }
                }
            }
        });
    }
}
