package com.anishmistry.glocal;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnBoarding extends AppCompatActivity {

    private ViewPager slideViewPager;
    private LinearLayout dotsLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] dots;
    private Button nextBtn, backBtn, finishBtn;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        backBtn = (Button)findViewById(R.id.back);
        nextBtn = (Button)findViewById(R.id.next);
        finishBtn = (Button)findViewById(R.id.finish);
        slideViewPager = (ViewPager)findViewById(R.id.slideViewPager);
        dotsLayout = (LinearLayout)findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        slideViewPager.setAdapter(sliderAdapter);
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
    }

    public void addDotsIndicator(int position) {
        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for(int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
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
            }

            else if(i == dots.length -1 ) {
                nextBtn.setEnabled(false);
                nextBtn.setVisibility(View.INVISIBLE);
                finishBtn.setEnabled(true);
                finishBtn.setVisibility(View.VISIBLE);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);
            }

            else {
                nextBtn.setEnabled(true);
                nextBtn.setVisibility(View.VISIBLE);
                finishBtn.setEnabled(false);
                finishBtn.setVisibility(View.INVISIBLE);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
