package com.anishmistry.glocal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterCategoryDetails extends Fragment implements MultiSelectSpinner.OnMultipleItemsSelectedListener {

    View view;
    Button signupBtn;
    RadioGroup radioExtraOptions;
    TextView extraOptions;
    public RegisterCategoryDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register_category_details, container, false);

        String[] array = {"Select Category", "Appliances", "Books", "Clothing", "Electronics", "Exercise & Fitness", "Home & Furniture", "Stationary", "Toys"};
        MultiSelectSpinner multiSelectSpinner = (MultiSelectSpinner)view.findViewById(R.id.sellerCategory);
        multiSelectSpinner.setItems(array);
        multiSelectSpinner.hasNoneOption(true);
        multiSelectSpinner.setSelection(new int[]{0});
        multiSelectSpinner.setListener(this);

        extraOptions = (TextView)view.findViewById(R.id.homeDelivery);
        radioExtraOptions = (RadioGroup)view.findViewById(R.id.radioHomeDelivery);
        signupBtn = (Button)view.findViewById(R.id.signup);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OTPAuth.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> str) {
        Toast.makeText(getContext(),"Selected Categories: " + str,Toast.LENGTH_LONG).show();
        if(str.contains("Clothing")) {
            extraOptions.setVisibility(View.VISIBLE);
            extraOptions.setText(R.string.trialRoom);
            radioExtraOptions.setVisibility(View.VISIBLE);
        }

        if((str.contains("Electronics")) || (str.contains("Home & Furniture")) || (str.contains("Appliances"))) {
            extraOptions.setVisibility(View.VISIBLE);
            extraOptions.setText(R.string.homeDelivery);
            radioExtraOptions.setVisibility(View.VISIBLE);
        }
    }

}
