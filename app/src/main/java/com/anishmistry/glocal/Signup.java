package com.anishmistry.glocal;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    ProgressDialog dialog;

    private ViewPager slideViewPager;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int currentPage;
    private RegisterationAdapter registerationAdapter;
    private Button nextBtn, backBtn, finishBtn;
//    private Button signupBtn;
//    private EditText name, phone, email;
    private MultiSelectSpinner sellerCategories;
    private TextView categoryOptions;
    private RadioGroup radioCateogryOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        backBtn = (Button)findViewById(R.id.back);
        nextBtn = (Button)findViewById(R.id.next);
        finishBtn = (Button)findViewById(R.id.finish);
        slideViewPager = (ViewPager)findViewById(R.id.slideViewPager);
        dotsLayout = (LinearLayout)findViewById(R.id.dotsLayout);
        registerationAdapter = new RegisterationAdapter(this);
        slideViewPager.setAdapter(registerationAdapter);
        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListener);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(currentPage + 1);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(currentPage - 1);
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(getApplicationContext(), Login.class);
                i.putExtra("onboard", true);
                startActivity(i);

            }
        });
//        name = (EditText)findViewById(R.id.sellerName);
//        phone = (EditText)findViewById(R.id.sellerPhone);
//        phone.setText("+91");
//        email = (EditText)findViewById(R.id.sellerEmail);
        registerationAdapter.phone.setText("+91");
        sellerCategories = (MultiSelectSpinner)findViewById(R.id.sellerCategory);
        categoryOptions = (TextView)findViewById(R.id.homeDelivery);
        radioCateogryOptions = (RadioGroup)findViewById(R.id.radioHomeDelivery);
        registerationAdapter.signup = (Button)findViewById(R.id.signup);
        registerationAdapter.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OTPAuth.class);
                intent.putExtra("phone", registerationAdapter.phone.getText().toString().trim());
                startActivity(intent);
            }
        });
    }

    public void addDotsIndicator(int position) {
        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for(int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorPrimary));
            dotsLayout.addView(dots[i]);
        }

        if(dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            currentPage = i;

            if(i==0) {
                nextBtn.setEnabled(true);
                nextBtn.setVisibility(View.VISIBLE);
                finishBtn.setEnabled(false);
                finishBtn.setVisibility(View.INVISIBLE);
                backBtn.setEnabled(false);
                backBtn.setVisibility(View.INVISIBLE);
                registerationAdapter.signup.setEnabled(false);
                registerationAdapter.signup.setVisibility(View.INVISIBLE);
                categoryOptions.setVisibility(View.INVISIBLE);
                radioCateogryOptions.setVisibility(View.INVISIBLE);
                sellerCategories.setVisibility(View.INVISIBLE);
                registerationAdapter.name.setVisibility(View.VISIBLE);
                registerationAdapter.phone.setVisibility(View.VISIBLE);
                registerationAdapter.email.setVisibility(View.VISIBLE);
            }

            else if(i == dots.length -1 ) {
                nextBtn.setEnabled(false);
                nextBtn.setVisibility(View.INVISIBLE);
                finishBtn.setEnabled(false);
                finishBtn.setVisibility(View.INVISIBLE);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);
                registerationAdapter.signup.setEnabled(true);
                registerationAdapter.signup.setVisibility(View.VISIBLE);
                categoryOptions.setVisibility(View.VISIBLE);
                radioCateogryOptions.setVisibility(View.VISIBLE);
                sellerCategories.setVisibility(View.VISIBLE);
                registerationAdapter.name.setVisibility(View.INVISIBLE);
                registerationAdapter.phone.setVisibility(View.INVISIBLE);
                registerationAdapter.email.setVisibility(View.INVISIBLE);

            }

            else {
                nextBtn.setEnabled(true);
                nextBtn.setVisibility(View.VISIBLE);
                finishBtn.setEnabled(false);
                finishBtn.setVisibility(View.INVISIBLE);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);
                registerationAdapter.name.setHint(R.string.shopName);
                registerationAdapter.phone.setHint(R.string.shopLocation);
                registerationAdapter.email.setHint(R.string.bidPrice);
                registerationAdapter.signup.setEnabled(false);
                registerationAdapter.signup.setVisibility(View.INVISIBLE);
                categoryOptions.setVisibility(View.INVISIBLE);
                radioCateogryOptions.setVisibility(View.INVISIBLE);
                sellerCategories.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
