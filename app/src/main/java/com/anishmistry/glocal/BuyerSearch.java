package com.anishmistry.glocal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BuyerSearch extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    ArrayList selectedCategoryList, shopList, shopAddressList, shopCategory;
    ArrayList<Integer> shopBidPrice;
    String selectedCategory;
    CardView cardView, cardView1, cardView2, cardView3, cardView4, cardView5;
    TextView textViewShopName, textViewShopAddress, textViewShopCategory, textViewShopRating;
    TextView textViewShopName1, textViewShopAddress1, textViewShopCategory1, textViewShopRating1;
    TextView textViewShopName2, textViewShopAddress2, textViewShopCategory2, textViewShopRating2;
    TextView textViewShopName3, textViewShopAddress3, textViewShopCategory3, textViewShopRating3;
    TextView textViewShopName4, textViewShopAddress4, textViewShopCategory4, textViewShopRating4;
    TextView textViewShopName5, textViewShopAddress5, textViewShopCategory5, textViewShopRating5;
//    TextView textViewShopName6, textViewShopAddress6, textViewShopCategory6, textViewShopRating6;
//    TextView textViewShopName7, textViewShopAddress7, textViewShopCategory7, textViewShopRating7;
    ImageView imageViewShopImage, imageViewShopImage1, imageViewShopImage2, imageViewShopImage3, imageViewShopImage4, imageViewShopImage5;
    ProgressDialog dialog;
//    RelativeLayout.LayoutParams cardLayoutParams, imageLayoutParams, shopNameLayoutParams, shopAddressLayoutParams, shopCategoryLayoutParams, shopRatingLayoutParams;
//    int shopNamePaddingLeft, shopNamePaddingTop;
//    int shopAddressPaddingLeft, shopAddressPaddingTop;
//    int shopCategoryPaddingLeft, shopCategoryPaddingTop;
//    int shopRatingPaddingLeft, shopRatingPaddingTop;
//    float cardRadius, shopNameTextSize, shopAddressTextSize, shopCategoryTextSize, shopRatingTextSize;
//    String shopNameText, shopAddressText, shopCategoryText, shopRatingText;
//    String cardViewId, cardViewIdDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        shopList = new ArrayList();
        shopAddressList = new ArrayList();
        shopCategory = new ArrayList();
        shopBidPrice = new ArrayList<Integer>();
        selectedCategoryList = getIntent().getParcelableArrayListExtra("chips");
        selectedCategory = TextUtils.join(", ", selectedCategoryList);
//        cardViewId = "R.id.cardView";

        cardView = findViewById(R.id.cardView);
        imageViewShopImage = findViewById(R.id.cardViewImage);
        textViewShopName = findViewById(R.id.cardViewShopName);
        textViewShopAddress = findViewById(R.id.cardViewShopAddress);
        textViewShopCategory = findViewById(R.id.cardViewShopCategory);
        textViewShopRating = findViewById(R.id.cardViewShopRating);

        cardView1 = findViewById(R.id.cardView1);
        imageViewShopImage1 = findViewById(R.id.cardViewImage1);
        textViewShopName1 = findViewById(R.id.cardViewShopName1);
        textViewShopAddress1 = findViewById(R.id.cardViewShopAddress1);
        textViewShopCategory1 = findViewById(R.id.cardViewShopCategory1);
        textViewShopRating1 = findViewById(R.id.cardViewShopRating1);

        cardView2 = findViewById(R.id.cardView2);
        imageViewShopImage2 = findViewById(R.id.cardViewImage2);
        textViewShopName2 = findViewById(R.id.cardViewShopName2);
        textViewShopAddress2 = findViewById(R.id.cardViewShopAddress2);
        textViewShopCategory2 = findViewById(R.id.cardViewShopCategory2);
        textViewShopRating2 = findViewById(R.id.cardViewShopRating2);

        cardView3 = findViewById(R.id.cardView3);
        imageViewShopImage3 = findViewById(R.id.cardViewImage3);
        textViewShopName3 = findViewById(R.id.cardViewShopName3);
        textViewShopAddress3 = findViewById(R.id.cardViewShopAddress3);
        textViewShopCategory3 = findViewById(R.id.cardViewShopCategory3);
        textViewShopRating3 = findViewById(R.id.cardViewShopRating3);

        cardView4 = findViewById(R.id.cardView);
        imageViewShopImage4 = findViewById(R.id.cardViewImage4);
        textViewShopName4 = findViewById(R.id.cardViewShopName4);
        textViewShopAddress4 = findViewById(R.id.cardViewShopAddress4);
        textViewShopCategory4 = findViewById(R.id.cardViewShopCategory4);
        textViewShopRating4 = findViewById(R.id.cardViewShopRating4);

        cardView5 = findViewById(R.id.cardView5);
        imageViewShopImage5 = findViewById(R.id.cardViewImage5);
        textViewShopName5 = findViewById(R.id.cardViewShopName5);
        textViewShopAddress5 = findViewById(R.id.cardViewShopAddress5);
        textViewShopCategory5 = findViewById(R.id.cardViewShopCategory5);
        textViewShopRating5 = findViewById(R.id.cardViewShopRating5);

//        cardView6 = findViewById(R.id.cardView6);
//        imageViewShopImage6 = findViewById(R.id.cardViewImage6);
//        textViewShopName6 = findViewById(R.id.cardViewShopName6);
//        textViewShopAddress6 = findViewById(R.id.cardViewShopAddress6);
//        textViewShopCategory6 = findViewById(R.id.cardViewShopCategory6);
//        textViewShopRating6 = findViewById(R.id.cardViewShopRating6);
//
//        cardView7 = findViewById(R.id.cardView7);
//        imageViewShopImage7 = findViewById(R.id.cardViewImage7);
//        textViewShopName7 = findViewById(R.id.cardViewShopName7);
//        textViewShopAddress7 = findViewById(R.id.cardViewShopAddress7);
//        textViewShopCategory7 = findViewById(R.id.cardViewShopCategory7);
//        textViewShopRating7 = findViewById(R.id.cardViewShopRating7);

//        cardLayoutParams = cardView.getLayoutParams();
//        imageLayoutParams = imageViewShopImage.getLayoutParams();
//        shopNameLayoutParams = textViewShopName.getLayoutParams();
//        shopAddressLayoutParams = textViewShopAddress.getLayoutParams();
//        shopCategoryLayoutParams = textViewShopCategory.getLayoutParams();
//        shopRatingLayoutParams = textViewShopRating.getLayoutParams();

//        shopNamePaddingLeft = textViewShopName.getPaddingLeft();
//        shopNamePaddingTop = textViewShopName.getPaddingTop();
//        shopNameText = textViewShopName.getText().toString();

//        shopAddressPaddingLeft = textViewShopAddress.getPaddingLeft();
//        shopAddressPaddingTop = textViewShopAddress.getPaddingTop();
//        shopAddressText = textViewShopAddress.getText().toString();

//        shopCategoryPaddingLeft = textViewShopCategory.getPaddingLeft();
//        shopCategoryPaddingTop = textViewShopCategory.getPaddingTop();
//        shopCategoryText = textViewShopCategory.getText().toString();

//        shopRatingPaddingLeft = textViewShopRating.getPaddingLeft();
//        shopRatingPaddingTop = textViewShopRating.getPaddingTop();
//        shopRatingText = textViewShopRating.getText().toString();

//        cardRadius = cardView.getRadius();
//        shopNameTextSize = textViewShopName.getTextSize();
//        shopAddressTextSize = textViewShopAddress.getTextSize();
//        shopCategoryTextSize = textViewShopCategory.getTextSize();
//        shopRatingTextSize = textViewShopRating.getTextSize();

//  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dialog = ProgressDialog.show(this, "", "Loading Results...", true);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userReference = mDatabaseReference.child("users").child("sellers");
        userReference
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) throws NumberFormatException {
                        if (dataSnapshot.getChildrenCount() > 0) {
                            HashMap<String, String> categoryMap = new HashMap();
                            HashMap<Integer, String> bidMap = new HashMap();
                            HashMap<String, String> addressMap = new HashMap();
                            String shop_name, shop_category, shop_address, shop_bid;
                            Integer i = 0;
                            int bid;
//                            String userCategory = "Electronics";
                            Log.d("Children Count", String.valueOf(dataSnapshot.getChildrenCount()));
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Log.d("shop_name", snapshot.child("shopName").getValue().toString());
                                Log.d("shop_cat",snapshot.child("selectedCategory").getValue().toString());
                                shop_name = snapshot.child("shopName").getValue().toString().trim();
                                shop_category = snapshot.child("selectedCategory").getValue().toString();
                                shop_address = snapshot.child("shopLocation").getValue().toString();
                                shop_bid = snapshot.child("bidPrice").getValue().toString();
                                bid = Integer.parseInt(shop_bid);
                                String shop_cat = shop_category.replaceAll("[\\[\\]\\(\\)]", "");
                                for(int j = 0; j < selectedCategoryList.size(); j++) {
                                    if (shop_cat.contains(selectedCategoryList.get(j).toString())) {
                                        categoryMap.put(shop_name, shop_cat);
                                        bidMap.put(bid, shop_name);
                                        addressMap.put(shop_name, shop_address);
                                        Log.d("map_size", String.valueOf(categoryMap.size()));
//                                    Boolean result = categoryMap.containsValue(userCategory);
//                                    Log.d("exists_cat",result.toString());
                                        shopList.add(shop_name);
                                        shopCategory.add(shop_cat);
                                        shopAddressList.add(shop_address);
                                        shopBidPrice.add(bid);
                                    }
                                }
                                i++;
                            }
                            if(shopCategory.isEmpty()) {
                                Intent intent = new Intent(getApplicationContext(), Page404.class);
                                startActivity(intent);
                            }
                            else {
                                bid = Collections.max(shopBidPrice);
                                int numberOfShops = categoryMap.size();
                                switch (numberOfShops) {
                                    case 1:
                                        cardView.setVisibility(View.VISIBLE);
                                        textViewShopName.setText(bidMap.get(bid).toString());
                                        textViewShopAddress.setText(addressMap.get(textViewShopName.getText().toString()));
                                        textViewShopCategory.setText(categoryMap.get(textViewShopName.getText().toString()));
                                        textViewShopRating.setText("0.0");
                                        if (dialog != null && dialog.isShowing())
                                            dialog.dismiss();
                                        break;

                                    case 2:
                                        cardView.setVisibility(View.VISIBLE);
                                        textViewShopName.setText(bidMap.get(bid).toString());
                                        textViewShopAddress.setText(addressMap.get(textViewShopName.getText().toString()));
                                        textViewShopCategory.setText(categoryMap.get(textViewShopName.getText().toString()));
                                        textViewShopRating.setText("0.0");

                                        cardView1.setVisibility(View.VISIBLE);
                                        textViewShopName1.setText(shopList.get(0).toString());
                                        textViewShopAddress1.setText(shopAddressList.get(0).toString());
                                        textViewShopCategory1.setText(shopCategory.get(0).toString());
                                        textViewShopRating1.setText("0.0");
                                        if (dialog != null && dialog.isShowing())
                                            dialog.dismiss();
                                        break;

                                    case 3:
                                        cardView.setVisibility(View.VISIBLE);
                                        textViewShopName.setText(bidMap.get(bid).toString());
                                        textViewShopAddress.setText(addressMap.get(textViewShopName.getText().toString()));
                                        textViewShopCategory.setText(categoryMap.get(textViewShopName.getText().toString()));
                                        textViewShopRating.setText("0.0");

                                        cardView1.setVisibility(View.VISIBLE);
                                        textViewShopName1.setText(shopList.get(0).toString());
                                        textViewShopAddress1.setText(shopAddressList.get(0).toString());
                                        textViewShopCategory1.setText(shopCategory.get(0).toString());
                                        textViewShopRating1.setText("0.0");

                                        cardView2.setVisibility(View.VISIBLE);
                                        textViewShopName2.setText(shopList.get(1).toString());
                                        textViewShopAddress2.setText(shopAddressList.get(1).toString());
                                        textViewShopCategory2.setText(shopCategory.get(1).toString());
                                        textViewShopRating2.setText("0.0");
                                        if (dialog != null && dialog.isShowing())
                                            dialog.dismiss();
                                        break;

                                    case 4:
                                        cardView.setVisibility(View.VISIBLE);
                                        textViewShopName.setText(bidMap.get(bid).toString());
                                        textViewShopAddress.setText(addressMap.get(textViewShopName.getText().toString()));
                                        textViewShopCategory.setText(categoryMap.get(textViewShopName.getText().toString()));
                                        textViewShopRating.setText("0.0");

                                        cardView1.setVisibility(View.VISIBLE);
                                        textViewShopName1.setText(shopList.get(0).toString());
                                        textViewShopAddress1.setText(shopAddressList.get(0).toString());
                                        textViewShopCategory1.setText(shopCategory.get(0).toString());
                                        textViewShopRating1.setText("0.0");

                                        cardView2.setVisibility(View.VISIBLE);
                                        textViewShopName2.setText(shopList.get(1).toString());
                                        textViewShopAddress2.setText(shopAddressList.get(1).toString());
                                        textViewShopCategory2.setText(shopCategory.get(1).toString());
                                        textViewShopRating2.setText("0.0");

                                        cardView3.setVisibility(View.VISIBLE);
                                        textViewShopName3.setText(shopList.get(2).toString());
                                        textViewShopAddress3.setText(shopAddressList.get(2).toString());
                                        textViewShopCategory3.setText(shopCategory.get(2).toString());
                                        textViewShopRating3.setText("0.0");
                                        if (dialog != null && dialog.isShowing())
                                            dialog.dismiss();
                                        break;

                                    case 5:
                                        cardView.setVisibility(View.VISIBLE);
                                        textViewShopName.setText(bidMap.get(bid).toString());
                                        textViewShopAddress.setText(addressMap.get(textViewShopName.getText().toString()));
                                        textViewShopCategory.setText(categoryMap.get(textViewShopName.getText().toString()));
                                        textViewShopRating.setText("0.0");

                                        cardView1.setVisibility(View.VISIBLE);
                                        textViewShopName1.setText(shopList.get(0).toString());
                                        textViewShopAddress1.setText(shopAddressList.get(0).toString());
                                        textViewShopCategory1.setText(shopCategory.get(0).toString());
                                        textViewShopRating1.setText("0.0");

                                        cardView2.setVisibility(View.VISIBLE);
                                        textViewShopName2.setText(shopList.get(1).toString());
                                        textViewShopAddress2.setText(shopAddressList.get(1).toString());
                                        textViewShopCategory2.setText(shopCategory.get(1).toString());
                                        textViewShopRating2.setText("0.0");

                                        cardView3.setVisibility(View.VISIBLE);
                                        textViewShopName3.setText(shopList.get(2).toString());
                                        textViewShopAddress3.setText(shopAddressList.get(2).toString());
                                        textViewShopCategory3.setText(shopCategory.get(2).toString());
                                        textViewShopRating3.setText("0.0");

                                        cardView4.setVisibility(View.VISIBLE);
                                        textViewShopName4.setText(shopList.get(3).toString());
                                        textViewShopAddress4.setText(shopAddressList.get(3).toString());
                                        textViewShopCategory4.setText(shopCategory.get(3).toString());
                                        textViewShopRating4.setText("0.0");
                                        if (dialog != null && dialog.isShowing())
                                            dialog.dismiss();
                                        break;
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        if (dialog != null && dialog.isShowing())
                            dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Firebase error occurred", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            Intent intent = new Intent(getApplicationContext(), BuyerDashboard.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.buyer_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            // Handle the camera action
        }
        else if (id == R.id.nav_faq) {

        }
        else if (id == R.id.nav_contact) {

        }
        else if (id == R.id.nav_rate) {

//        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
