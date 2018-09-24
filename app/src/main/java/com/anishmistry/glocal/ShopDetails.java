package com.anishmistry.glocal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tomergoldst.tooltips.ToolTip;
import com.tomergoldst.tooltips.ToolTipsManager;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ShopDetails extends AppCompatActivity {

    RelativeLayout relativeLayout;
    Button buttonReview, buttonLocation, buttonShare, buttonCall, buttonCostInfo;
    TextView textViewShopName, textViewShopCategory, textViewShopAddress, textViewShopRating, textViewShopTimings, textViewBestsellers;
    TextView textViewAverageCost, textViewPaymentModes, textMoreInfo;
    String shop_name, shop_address, shop_category, shop_rating, shop_bestsellers, shop_average_cost, shop_payment_modes, shop_phone;
    String shop_timings, source, destination, more_info;
    ArrayList allShops;
    ImageView imageViewShopImage;
    ToolTipsManager toolTipsManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);

        relativeLayout = findViewById(R.id.relative);

        buttonReview = findViewById(R.id.btnReview);
        buttonLocation = findViewById(R.id.btnLocation);
        buttonShare = findViewById(R.id.btnShare);
        buttonCall = findViewById(R.id.btnCall);
        buttonCostInfo = findViewById(R.id.costInfo);

        textViewShopName = findViewById(R.id.shopName);
        textViewShopCategory = findViewById(R.id.shopCategory);
        textViewShopAddress = findViewById(R.id.shopAddress);
        textViewShopRating = findViewById(R.id.shopRating);
        textViewShopTimings = findViewById(R.id.shopTiming);
        textViewBestsellers = findViewById(R.id.bestseller);
        textViewAverageCost = findViewById(R.id.averageCost);
        textViewPaymentModes = findViewById(R.id.paymentMode);
        textMoreInfo = findViewById(R.id.moreInfo);

        imageViewShopImage = findViewById(R.id.shopImage);

        shop_name = getIntent().getExtras().getString("shopName");
        shop_category = getIntent().getExtras().getString("shopCategory");
        shop_address = getIntent().getExtras().getString("shopAddress");
        allShops = getIntent().getParcelableArrayListExtra("allShops");
        source = "12.934659, 77.605969";

        if(shop_name.equals(allShops.get(0))) {
            shop_rating = "3.9";
            shop_timings = "(10am - 10pm)";
            shop_phone = "08041238240";
            shop_bestsellers = "Table, Bed, Mattresses, Office Furniture";
            shop_payment_modes = "Cash and Cards accepted";
            shop_average_cost = "\u20B9\u20B9";
            imageViewShopImage.setImageResource(R.drawable.one);
            destination = "12.986761, 77.602656";
            more_info = "Home delivery not available";
        }

        else if(shop_name.equals(allShops.get(1))) {
            shop_rating = "4.2";
            shop_timings = "(10am - 10pm)";
            shop_phone = "08041470798";
            shop_bestsellers = "Laptops, Desktops, Computer Accessories";
            shop_payment_modes = "Cash and Cards accepted";
            shop_average_cost = "\u20B9";
            imageViewShopImage.setImageResource(R.drawable.two);
            destination = "12.928071, 77.609069";
            more_info = "Home delivery available";
        }

        else if(shop_name.equals(allShops.get(2))) {
            shop_rating = "4.1";
            shop_timings = "(10am - 9pm)";
            shop_phone = "+919845196670";
            shop_bestsellers = "Men's Clothing, Kid's Clothing, Western Wear";
            shop_payment_modes = "Cash and Cards accepted";
            shop_average_cost = "\u20B9";
            imageViewShopImage.setImageResource(R.drawable.three);
            destination = "13.003621, 77.624534";
            more_info = "Trial room available";
        }

        else if(shop_name.equals(allShops.get(3))) {
            shop_rating = "4.1";
            shop_timings = "(10am - 9pm)";
            shop_phone = "08042192913";
            shop_bestsellers = "Sofa, Bed, Dining Table";
            shop_payment_modes = "Cash, Cards and Cheques accepted";
            shop_average_cost = "\u20B9\u20B9";
            imageViewShopImage.setImageResource(R.drawable.four);
            destination = "12.916682, 77.602624";
            more_info = "Home delivery available";
        }

        else if(shop_name.equals(allShops.get(4))) {
            shop_rating = "4.2";
            shop_timings = "(11am - 8pm)";
            shop_phone = "07207666000";
            shop_bestsellers = "Laptops, Desktops, Computer Accessories, Appliances";
            shop_payment_modes = "Cash and Cards accepted";
            shop_average_cost = "\u20B9\u20B9\u20B9 ";
            imageViewShopImage.setImageResource(R.drawable.five);
            destination = "12.938066, 77.627475";
            more_info = "Home delivery not available";

        }

        else if(shop_name.equals(allShops.get(5))) {
            shop_rating = "3.8";
            shop_timings = "(11am - 9pm)";
            shop_phone = "08025501012";
            shop_bestsellers = "Home Decor, Sofa, Cabinet, Dining Table, Bed";
            shop_payment_modes = "Cash and Cards accepted";
            shop_average_cost = "\u20B9\u20B9";
            imageViewShopImage.setImageResource(R.drawable.seven);
            destination = "12.937364,77.609351";
            more_info = "Home delivery not available";
        }

        buttonCostInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolTipsManager = new ToolTipsManager();
                ToolTip.Builder builder = new ToolTip.Builder(getApplicationContext(), buttonCostInfo, relativeLayout, "₹ - Cheap \n ₹₹ - Moderate \n ₹₹₹ - Expensive", ToolTip.POSITION_RIGHT_TO);
                builder.setBackgroundColor(getResources().getColor(R.color.colorChipBackgroundClicked));
                toolTipsManager.show(builder.build());
            }
        });

        textViewShopName.setText(shop_name);
        textViewShopCategory.setText(shop_category);
        textViewShopAddress.setText(shop_address);
        textViewShopRating.setText(shop_rating);
        textViewShopTimings.setText(shop_timings);
        textViewBestsellers.setText(shop_bestsellers);
        textViewAverageCost.setText(shop_average_cost);
        textViewPaymentModes.setText(shop_payment_modes);
        textMoreInfo.setText(more_info);

        buttonReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddReview.class);
                intent.putExtra("shopName", shop_name);
                startActivity(intent);
            }
        });

        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?saddr=" + source + "&daddr="+ destination;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shop_name + "\n" + shop_address);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+shop_phone));
                startActivity(callIntent);
            }
        });

    }
}
