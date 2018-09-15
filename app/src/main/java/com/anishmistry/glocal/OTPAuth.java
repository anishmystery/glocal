package com.anishmistry.glocal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;
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

    private String seller_phone;
    private String buyer_phone;

    UserType ut = new UserType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpauth);

        editTextMobile = findViewById(R.id.phone);
        buttonSendOTP = findViewById(R.id.sendOTP);
        resendOTP = findViewById(R.id.resendOTP);
        buttonVerifyOTP = findViewById(R.id.verifyOTP);
        enterOTP = findViewById(R.id.inputOTP);

        seller_phone = getIntent().getStringExtra("sellerPhone");
        buyer_phone = getIntent().getStringExtra("buyerPhone");

        if(seller_phone == null) {
            editTextMobile.setText(buyer_phone);
            ut.setUserType("Buyer");
        }

        else {
            editTextMobile.setText(seller_phone);
            ut.setUserType("Seller");
        }

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
        span.setSpan(clickableSpan, 25, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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
                enterOTP.setText(phoneAuthCredential.getSmsCode().toString());
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
                if (enterOTP != null) {
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
                if (task.isSuccessful()) {
                    Toast.makeText(OTPAuth.this, "OTP verified", Toast.LENGTH_SHORT).show();
                    boolean fromLogin = getIntent().getExtras().getBoolean("fromLoginActivity");
                    if (fromLogin == false) {

                        if(ut.userType.equals("Seller")) {
                            writeNewSeller(
                                    getIntent().getExtras().getString("sellerName"),
                                    getIntent().getExtras().getString("sellerPhone"),
                                    getIntent().getExtras().getString("sellerEmail"),
                                    getIntent().getExtras().getString("shopName"),
                                    getIntent().getExtras().getString("shopLocation"),
                                    getIntent().getExtras().getString("bidPrice"),
                                    Arrays.asList(getIntent().getExtras().getStringArray("selectedCategory")),
                                    getIntent().getExtras().getString("selectedRadioButton"));
                        }

                        else {
                            writeNewBuyer(
                                    getIntent().getExtras().getString("buyerName"),
                                    getIntent().getExtras().getString("buyerPhone"),
                                    getIntent().getExtras().getString("buyerEmail"),
                                    getIntent().getExtras().getString("buyerDOB"),
                                    getIntent().getExtras().getString("buyerGender"));
                        }
                    } else {
                        if(getIntent().getExtras().getString("buyerPhone").equals("")) {
                            Intent loginIntent = new Intent(OTPAuth.this, SellerDashboard.class);
                            startActivity(loginIntent);
                            finish();
                        }
                        else {
                            Intent loginIntent = new Intent(OTPAuth.this, BuyerDashboard.class);
                            startActivity(loginIntent);
                            finish();
                        }
                    }

                } else {
                    Toast.makeText(OTPAuth.this, "Invalid OTP Entered", Toast.LENGTH_SHORT).show();
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                    }
                }
            }
        });
    }

    private void writeNewSeller(final String sellerName, String sellerPhone, String sellerEmail, String shopName, String shopLocation,
                              String bidPrice, List selectedCategory, String selectedExtraOption) {
        AddSeller seller = new AddSeller(sellerName, sellerPhone, sellerEmail, shopName, shopLocation, bidPrice,
                Arrays.asList(selectedCategory), selectedExtraOption);
        DatabaseReference rootRef = databaseReference.child("users");
//        final DatabaseReference userRef = rootRef.push();
        DatabaseReference sellerRef = rootRef.child("sellers");
        final DatabaseReference userRef = sellerRef.push();
        userRef.setValue(seller).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                SharedPreferences pref = getSharedPreferences("docId", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("documentID", userRef.getKey());
                editor.apply();
                Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(OTPAuth.this, SellerDashboard.class);
                loginIntent.putExtra("sellerName", sellerName);
                startActivity(loginIntent);
                finish();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Registration: ", "Error was: " + e.getMessage() + "");
                Toast.makeText(getApplicationContext(), "Could not complete registraton. Please try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void writeNewBuyer(final String buyerName, String buyerPhone, String buyerEmail, String buyerDOB, String buyerGender) {
        AddBuyer buyer = new AddBuyer(buyerName, buyerPhone, buyerEmail, buyerDOB, buyerGender);
        DatabaseReference rootRef = databaseReference.child("users");
//        final DatabaseReference userRef = rootRef.push();
        DatabaseReference buyerRef = rootRef.child("buyers");
        final DatabaseReference userRef = buyerRef.push();
        userRef.setValue(buyer).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                SharedPreferences pref = getSharedPreferences("docId", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("documentID", userRef.getKey());
                editor.apply();
                Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(OTPAuth.this, BuyerDashboard.class);
                loginIntent.putExtra("buyerName", buyerName);
                startActivity(loginIntent);
                finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Registration: ", "Error was: " + e.getMessage() + "");
                        Toast.makeText(getApplicationContext(), "Could not complete registraton. Please try again later", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
