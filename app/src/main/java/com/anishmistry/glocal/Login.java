package com.anishmistry.glocal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Login extends AppCompatActivity {
    Button buttonLogin;
    EditText editTextPhone;
    TextView textViewSignup;
    Boolean fromOnboarding;
    String spanString, mobileNumber;
    List sellerList;

    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private SharedPreferences sharedpreferences;
    private String userID;
    UserType ut = new UserType();

    public static final String SF_NAME = "SharedPref" ;

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
        editTextPhone.setText("+91");
        buttonLogin = findViewById(R.id.login);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
       /*  mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
                if(mUser != null){
                    userID = mUser.getUid();
                    Log.d("UID", userID);
                    sharedpreferences = getApplicationContext().getSharedPreferences(SF_NAME, Context.MODE_PRIVATE);
                    final String docID = sharedpreferences.getString("documentID", null);
                    Log.d("DOC_ID", "" + docID + "");

                    if (TextUtils.isEmpty(mobileNumber)) {
                        mobileNumber = mUser.getPhoneNumber().substring(3);
                    }
                    Log.d("NUMBER", mobileNumber);
                    DatabaseReference userRef = mDatabaseReference.child("users");

                    //check if user=>passenger
                    DatabaseReference passengersRef = userRef.child("sellers");
                    Query query =passengersRef.orderByChild("sellerPhone").equalTo(mobileNumber);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Log.d("P_COUNT",String.valueOf(dataSnapshot.getChildrenCount()));
                            if(dataSnapshot.getChildrenCount() > 0){
                                Intent intent= new Intent(getApplicationContext(),SellerDashboard.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.d("CLASS", "Firebase Error");
                        }
                    });

                    //check if user=>rider
                    DatabaseReference riderRef = userRef.child("buyers");
                    Query query1 =riderRef.orderByChild("buyerPhone").equalTo(mobileNumber);
                    query1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Log.d("R_COUNT",String.valueOf(dataSnapshot.getChildrenCount()));
                            if(dataSnapshot.getChildrenCount() > 0){
                                Intent intent= new Intent(getApplicationContext(),BuyerDashboard.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.d("CLASS", "Firebase Error");
                        }
                    });
                } //user not logged in
                */
//     mDatabaseReference = FirebaseDatabase.getInstance().getReference();
//     FirebaseUser mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        if(mFirebaseUser != null){
//            Log.d("LOGIN_UID", String.valueOf(mFirebaseAuth.getUid()));
//            sharedpreferences = getApplicationContext().getSharedPreferences(SF_NAME, Context.MODE_PRIVATE);
//            final String docID = sharedpreferences.getString("documentID", null);
//            Log.d("DOC_ID", "" + docID + "");
//
//
//            DatabaseReference userRef = mDatabaseReference.child("users");
//            DatabaseReference sellerRef = userRef.child("sellers");
//            DatabaseReference ref = sellerRef.child(docID);
//            ref.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    if(dataSnapshot.exists()){
//                        Log.d("SUCCESS", "Sellect");
//                    }
//                    if(!dataSnapshot.exists()){
//                        Log.d("!SUCCESS", "Sellect");
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//            }

//        docIdSeller();
//        docIdBuyer();
//        if (mFirebaseAuth.getCurrentUser() != null) {
//            String user_type = ut.getUserType();
//            if(user_type == "Buyer") {
//                Intent mainIntent = new Intent(Login.this, BuyerDashboard.class);
//                startActivity(mainIntent);
//                finish();
//            }
//            else {
//                Intent mainIntent = new Intent(Login.this, SellerDashboard.class);
//                startActivity(mainIntent);
//                finish();
//            }
//        }


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextPhone.getText())) {
                    editTextPhone.setError("Phone Number is required");
                    return;
                } else if (!android.util.Patterns.PHONE.matcher(editTextPhone.getText().toString()).matches()) {
                    editTextPhone.setError("Invalid Number Entered");
                    return;
                } else {
                    String number = editTextPhone.getText().toString();
                    checkSellerExists(number);
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
                        Intent intent = new Intent(getApplicationContext(), Signup.class);
                        intent.putExtra("fromLogin","seller");
                        startActivity(intent);
                    }
                })
                .setNegativeText("Buyer")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent = new Intent(getApplicationContext(), Signup.class);
                        intent.putExtra("fromLogin","buyer");
                        startActivity(intent);
                    }
                });

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

    public void checkSellerExists(final String number) {

        DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userReference = rootReference.child("users").child("sellers");
                userReference
                        .orderByChild("sellerPhone")
                        .equalTo(number)
                        .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Intent intentOTP = new Intent(getApplicationContext(), OTPAuth.class);
//                                intentOTP.putExtra("buyerPhone", number);
                            intentOTP.putExtra("sellerPhone", number);
                            intentOTP.putExtra("fromLoginActivity", true);
                            startActivity(intentOTP);
                        } else {
                            checkBuyerExists(number);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Firebase error occurred", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
    }

    public void checkBuyerExists(final String number) {

        DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userReference = rootReference.child("users").child("buyers");
        userReference
                .orderByChild("buyerPhone")
                .equalTo(number)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Intent intentOTP = new Intent(getApplicationContext(), OTPAuth.class);
//                                intentOTP.putExtra("buyerPhone", number);
                            intentOTP.putExtra("buyerPhone", number);
                            intentOTP.putExtra("fromLoginActivity", true);
                            startActivity(intentOTP);
                            finish();
                        } else {
                            editTextPhone.setError("This number is not registered");
                            return;
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Firebase error occurred", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
    }

//    public void docIdSeller() {
//
//        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        if (firebaseAuth.getCurrentUser() != null) {
////            if (TextUtils.isEmpty(mobileNumber)) {
//              String phone = firebaseAuth.getCurrentUser().getPhoneNumber();
////            }
//            Log.d("NUMBER", " " + phone + "");
//
//            final Query query = databaseRef.child("users").child("sellers").orderByChild("sellerPhone").equalTo(phone);
//            query.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.getChildrenCount() > 0) {
//                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                            sellerList.add(postSnapshot.getKey());
//                        }
//                        final String docID = String.valueOf(sellerList.get(0));
//                        SharedPreferences sharedPreferences = getSharedPreferences(SF_NAME, MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("documentID", docID);
//                        editor.apply();
//                        editor.commit();
//
//                        Log.d("docID", "" + docID + "");
//
//                        Intent mainIntent = new Intent();
//                        mainIntent.setClass(Login.this, SellerDashboard.class);
//                        startActivity(mainIntent);
//                        finish();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//        }
//        else {
//            Log.d("Login", "LoginError");
//        }
//    }
//
//    public void docIdBuyer() {
//        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        if (firebaseAuth.getCurrentUser() != null) {
////            if (TextUtils.isEmpty(mobileNumber)) {
//                String phone = firebaseAuth.getCurrentUser().getPhoneNumber();
////            }
//            Log.d("NUMBER", " " + mobileNumber + "");
//
//            final Query query = databaseRef.child("users").child("buyers").orderByChild("buyerPhone").equalTo(phone);
//            query.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.getChildrenCount() > 0) {
//                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                            sellerList.add(postSnapshot.getKey());
//                        }
//                        final String docID = String.valueOf(sellerList.get(0));
//                        SharedPreferences sharedPreferences = getSharedPreferences(SF_NAME, MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("documentID", docID);
//                        editor.apply();
//                        editor.commit();
//
//                        Log.d("docID", "" + docID + "");
//
//                        Intent mainIntent = new Intent();
//                        mainIntent.setClass(Login.this, SellerDashboard.class);
//                        startActivity(mainIntent);
//                        finish();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//        } else {
//            Log.d("Login", "LoginError");
//        }
//    }

}
