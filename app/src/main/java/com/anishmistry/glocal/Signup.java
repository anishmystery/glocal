package com.anishmistry.glocal;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Signup extends AppCompatActivity {

    String fromLogin;
//    private LinearLayout dotsLayout;
//    private TextView[] dots;
//    private Button buttonNext, buttonBack;
//    private int counter = 0;
//    private boolean validPhone, validEmail;
//    private String name, phone, email;
//    private EditText editTextSellerName, editTextSellerPhone, editTextSellerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fromLogin = getIntent().getStringExtra("fromLogin");
        if(fromLogin.equals("seller")) {
            Fragment fragment;
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragment = new RegisterSellerDetails();
            fragmentTransaction.replace(R.id.container, fragment);
            getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    if(getFragmentManager().getBackStackEntryCount() == 0) finish();
                }
            });
            fragmentTransaction.commit();
        }
        else {
            Fragment fragment;
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragment = new RegisterBuyerDetails();
            fragmentTransaction.replace(R.id.container, fragment);
            getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    if(getFragmentManager().getBackStackEntryCount() == 0) finish();
                }
            });
            fragmentTransaction.commit();
        }
//        editTextSellerPhone = (EditText)findViewById(R.id.buyerPhone);
//        buttonBack = (Button)findViewById(R.id.back);
//        buttonNext = (Button)findViewById(R.id.next);
//        dotsLayout = (LinearLayout)findViewById(R.id.dotsLayout);
//        buttonNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                counter++;
//                if(counter==1) {
//                    addDotsIndicator(1);
//                    buttonBack.setEnabled(true);
//                    buttonBack.setVisibility(View.VISIBLE);
//                    buttonNext.setEnabled(true);
//                    buttonNext.setVisibility(View.VISIBLE);
//                    Fragment fragment = new RegisterShopDetails();
//                    fragment.setArguments(bundle);
//                    FragmentManager fm = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
//                    fragmentTransaction.replace(R.id.container, fragment);
//                    fragmentTransaction.commit();
//
////                    if(TextUtils.isEmpty(editTextSellerName.getText().toString())) {
////                        editTextSellerName.setError("Seller name required");
////                    }
////
////                    else if(TextUtils.isEmpty(editTextSellerPhone.getText().toString())) {
////                        editTextSellerPhone.setError("Seller phone number required");
////                    }
////
////                    else if(TextUtils.isEmpty(editTextSellerEmail.getText().toString())) {
////                        editTextSellerEmail.setError("Seller email required");
////                    }
////
////                    else {
////                        validPhone = Patterns.PHONE.matcher(editTextSellerPhone.getText().toString().trim()).matches();
////                        validEmail = Patterns.PHONE.matcher(editTextSellerEmail.getText().toString().trim()).matches();
////                        if(validPhone == false) {
////                            editTextSellerPhone.setError("Invalid phone number");
////                        }
////
////                        else if(validEmail == false) {
////                            editTextSellerEmail.setError("Invalid email");
////                        }
//
////                        else {
////                            name = editTextSellerName.getText().toString().trim();
////                            phone = editTextSellerPhone.getText().toString().trim();
////                            email = editTextSellerEmail.getText().toString().trim();
//
////                            bundle.putString("buyerName", name);
////                            bundle.putString("buyerPhone", phone);
////                            bundle.putString("buyerEmail", email);
////                        }
////                    }
//
//                }
//
//                else {
//                    Fragment fragment = new RegisterCategoryDetails();
//                    FragmentManager fm = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
//                    fragmentTransaction.replace(R.id.container, fragment);
//                    fragmentTransaction.commit();
//                    addDotsIndicator(2);
//                    buttonBack.setEnabled(true);
//                    buttonBack.setVisibility(View.VISIBLE);
//                    buttonNext.setEnabled(false);
//                    buttonNext.setVisibility(View.INVISIBLE);
//                }
//
//            }
//        });
//
//        buttonBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                counter--;
//                if(counter==1) {
//                    Fragment fragment = new RegisterShopDetails();
//                    FragmentManager fm = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
//                    fragmentTransaction.replace(R.id.container, fragment);
//                    fragmentTransaction.commit();
//                    addDotsIndicator(1);
//                    buttonBack.setEnabled(true);
//                    buttonBack.setVisibility(View.VISIBLE);
//                    buttonNext.setEnabled(true);
//                    buttonNext.setVisibility(View.VISIBLE);
//                }
//
//                else {
//                    Fragment fragment = new RegisterSellerDetails();
//                    FragmentManager fm = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
//                    fragmentTransaction.replace(R.id.container, fragment);
//                    fragmentTransaction.commit();
//                    addDotsIndicator(0);
//                    buttonBack.setEnabled(false);
//                    buttonBack.setVisibility(View.INVISIBLE);
//                    buttonNext.setEnabled(true);
//                    buttonNext.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//        if(counter==0) {
//            addDotsIndicator(0);
//            buttonBack.setEnabled(false);
//            buttonBack.setVisibility(View.INVISIBLE);
//            buttonNext.setEnabled(true);
//            buttonNext.setVisibility(View.VISIBLE);
//        editTextSellerPhone.setText("+91");
//        }
    }
//
//    public void addDotsIndicator(int position) {
//        dots = new TextView[3];
//        dotsLayout.removeAllViews();
//
//        for(int i = 0; i < dots.length; i++) {
//            dots[i] = new TextView(this);
//            dots[i].setText(Html.fromHtml("&#8226;"));
//            dots[i].setTextSize(35);
//            dots[i].setTextColor(getResources().getColor(R.color.colorPrimary));
//            dotsLayout.addView(dots[i]);
//        }
//            dots[position].setTextColor(getResources().getColor(R.color.colorAccent));
//    }
}
