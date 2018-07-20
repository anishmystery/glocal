package com.anishmistry.glocal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int [] images = {
            R.drawable.seller,
            R.drawable.promote,
            R.drawable.buyer,
            R.drawable.discover
    };

    public String [] headings = {
            "SELLER ONBOARDING",
            "PROMOTE YOUR BUSINESS",
            "BUYER ONBOARDING",
            "DISCOVER SHOPS"
    };

    public String [] descriptions = {
            "Register your shop and become a seller, showcasing a variety of products for users to buy",
            "Set a bid price to make your shop discoverable and accessible easily to the target audience",
            "Register as a buyer and search for shops selling the desired products",
            "Discover shops based on the category of products and the distance"
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImage = (ImageView)view.findViewById(R.id.slideImage);
        TextView slideHeading = (TextView)view.findViewById(R.id.slideHeading);
        TextView slideDescription = (TextView)view.findViewById(R.id.slideDescription);

        slideImage.setImageResource(images[position]);
        slideHeading.setText(headings[position]);
        slideDescription.setText(descriptions[position]);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);
    }
}
