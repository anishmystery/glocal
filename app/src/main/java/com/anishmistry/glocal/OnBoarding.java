package com.anishmistry.glocal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
    private Button buttonNext, buttonBack, buttonFinish;
    private int currentPage;
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            currentPage = i;

            if (i == 0) {
                buttonNext.setEnabled(true);
                buttonNext.setVisibility(View.VISIBLE);
                buttonFinish.setEnabled(false);
                buttonFinish.setVisibility(View.INVISIBLE);
                buttonBack.setEnabled(false);
                buttonBack.setVisibility(View.INVISIBLE);
            } else if (i == dots.length - 1) {
                buttonNext.setEnabled(false);
                buttonNext.setVisibility(View.INVISIBLE);
                buttonFinish.setEnabled(true);
                buttonFinish.setVisibility(View.VISIBLE);
                buttonBack.setEnabled(true);
                buttonBack.setVisibility(View.VISIBLE);
            } else {
                buttonNext.setEnabled(true);
                buttonNext.setVisibility(View.VISIBLE);
                buttonFinish.setEnabled(false);
                buttonFinish.setVisibility(View.INVISIBLE);
                buttonBack.setEnabled(true);
                buttonBack.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        buttonBack = (Button) findViewById(R.id.back);
        buttonNext = (Button) findViewById(R.id.next);
        buttonFinish = (Button) findViewById(R.id.finish);
        slideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        dotsLayout = (LinearLayout) findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        slideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListener);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(currentPage + 1);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(currentPage - 1);
            }
        });

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(getApplicationContext(), Login.class);
                i.putExtra("fromOnboarding", true);
                startActivity(i);

            }
        });
    }

    public void addDotsIndicator(int position) {
        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }
}
