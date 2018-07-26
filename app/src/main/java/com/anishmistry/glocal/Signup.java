package com.anishmistry.glocal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Signup extends AppCompatActivity {

    private LinearLayout dotsLayout;
    private TextView[] dots;
    private Button nextBtn, backBtn;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        backBtn = (Button)findViewById(R.id.back);
        nextBtn = (Button)findViewById(R.id.next);
        dotsLayout = (LinearLayout)findViewById(R.id.dotsLayout);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                if(counter==1) {
                    Fragment fragment = new RegisterShopDetails();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.commit();
                    addDotsIndicator(1);
                    backBtn.setEnabled(true);
                    backBtn.setVisibility(View.VISIBLE);
                    nextBtn.setEnabled(true);
                    nextBtn.setVisibility(View.VISIBLE);
                }

                else {
                    Fragment fragment = new RegisterCategoryDetails();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.commit();
                    addDotsIndicator(2);
                    backBtn.setEnabled(true);
                    backBtn.setVisibility(View.VISIBLE);
                    nextBtn.setEnabled(false);
                    nextBtn.setVisibility(View.INVISIBLE);
                }

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                if(counter==1) {
                    Fragment fragment = new RegisterShopDetails();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.commit();
                    addDotsIndicator(1);
                    backBtn.setEnabled(true);
                    backBtn.setVisibility(View.VISIBLE);
                    nextBtn.setEnabled(true);
                    nextBtn.setVisibility(View.VISIBLE);
                }

                else {
                    Fragment fragment = new RegisterSellerDetails();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.commit();
                    addDotsIndicator(0);
                    backBtn.setEnabled(false);
                    backBtn.setVisibility(View.INVISIBLE);
                    nextBtn.setEnabled(true);
                    nextBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        if(counter==0) {
            addDotsIndicator(0);
            backBtn.setEnabled(false);
            backBtn.setVisibility(View.INVISIBLE);
            nextBtn.setEnabled(true);
            nextBtn.setVisibility(View.VISIBLE);
            Fragment fragment = new RegisterSellerDetails();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.commit();
        }
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
            dots[position].setTextColor(getResources().getColor(R.color.colorAccent));
    }
}
