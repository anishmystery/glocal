package com.anishmistry.glocal;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterCategoryDetails extends Fragment implements MultiSelectSpinner.OnMultipleItemsSelectedListener {

    View view;
    Bundle bundle;
    TextView textViewDots1, textViewDots2, textViewDots3, errorMessage;
    Button buttonSignup;
    RadioGroup radioGroupExtraOptions;
    RadioButton radioButtonExtraOptions;
    TextView textViewExtraOptions;
    ProgressDialog dialog;
    private Button buttonBack;
    private String[] selectedCategory;
    private int selectedRadioButtonId;

    public RegisterCategoryDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register_category_details, container, false);

        String[] array = {"Select Category", "Appliances", "Books", "Clothing", "Electronics", "Exercise & Fitness", "Home & Furniture", "Stationary", "Toys"};
        MultiSelectSpinner multiSelectSpinner = (MultiSelectSpinner) view.findViewById(R.id.sellerCategory);
        multiSelectSpinner.setItems(array);
        multiSelectSpinner.hasNoneOption(true);
        multiSelectSpinner.setSelection(new int[]{0});
        multiSelectSpinner.setListener(this);

        textViewExtraOptions = (TextView) view.findViewById(R.id.homeDelivery);
        radioGroupExtraOptions = (RadioGroup) view.findViewById(R.id.radioHomeDelivery);
        buttonBack = view.findViewById(R.id.back);
        textViewDots1 = view.findViewById(R.id.dots1);
        textViewDots2 = view.findViewById(R.id.dots2);
        textViewDots3 = view.findViewById(R.id.dots3);
        errorMessage = view.findViewById(R.id.errorMessage);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragment = new RegisterShopDetails();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                textViewDots2.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });

        buttonSignup = (Button) view.findViewById(R.id.signup);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedRadioButton;
                final String sellerName = getArguments().getString("sellerName");
                final String sellerPhone = getArguments().getString("sellerPhone");
                final String sellerEmail = getArguments().getString("sellerEmail");
                final String shopName = getArguments().getString("shopName");
                final String shopLocation = getArguments().getString("shopLocation");
                final String bidPrice = getArguments().getString("bidPrice");
                selectedRadioButtonId = radioGroupExtraOptions.getCheckedRadioButtonId();
                radioButtonExtraOptions = (RadioButton) view.findViewById(selectedRadioButtonId);
                if(radioButtonExtraOptions == null)
                    selectedRadioButton = "N/A";
                else
                    selectedRadioButton = radioButtonExtraOptions.getText().toString();


                checkUserExists(sellerName, sellerPhone, sellerEmail, shopName, shopLocation, bidPrice, selectedCategory,
                        selectedRadioButton, false);
//                Intent intent = new Intent(getActivity(), OTPAuth.class);
//                intent.putExtra("buyerName", name);
//                intent.putExtra("buyerPhone", phoneNumber);
//                intent.putExtra("buyerEmail", email);
//                intent.putExtra("buyerDOB", buyerDOB);
//                intent.putExtra("shopLocation", shopLocation);
//                intent.putExtra("bidPrice", bidPrice);
//                intent.putExtra("selectedCategory", selectedCategory);
//                intent.putExtra("selectedRadioButton", selectedRadioButton);
//                startActivity(intent);
            }
        });



        return view;
    }

    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> str) {
        selectedCategory = new String[str.size()];
        Toast.makeText(getActivity(), "Selected Categories: " + str, Toast.LENGTH_LONG).show();
        if (str.contains("Clothing")) {
            textViewExtraOptions.setVisibility(View.VISIBLE);
            textViewExtraOptions.setText(R.string.trialRoom);
            radioGroupExtraOptions.setVisibility(View.VISIBLE);
        }

        if ((str.contains("Electronics")) || (str.contains("Home & Furniture")) || (str.contains("Appliances"))) {
            textViewExtraOptions.setVisibility(View.VISIBLE);
            textViewExtraOptions.setText(R.string.homeDelivery);
            radioGroupExtraOptions.setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < str.size(); i++) {
            selectedCategory[i] = str.get(i);
        }
    }

    public void checkUserExists(final String sellerName, final String sellerPhone, final String sellerEmail, final String shopName,
                                final String shopLocation, final String bidPrice, final String[] selectedCategory,
                                final String selectedRadioButton, final boolean whichActivity) {
        dialog = ProgressDialog.show(getActivity(), "Registration", "Creating User", true);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("users")
                .orderByChild("sellerPhone")
                .equalTo(sellerPhone)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            errorMessage.setError("Account with this phone number already exists!");
                            if (dialog != null && dialog.isShowing())
                                dialog.dismiss();
                            return;
                        } else {
                            //Toast.makeText(getApplicationContext(), "Creating new user", Toast.LENGTH_SHORT).show();
                            if (dialog != null && dialog.isShowing())
                                dialog.dismiss();
                            Intent intent = new Intent(getActivity(), OTPAuth.class);
                            intent.putExtra("sellerName", sellerName);
                            intent.putExtra("sellerPhone", sellerPhone);
                            intent.putExtra("sellerEmail", sellerEmail);
                            intent.putExtra("shopName", shopName);
                            intent.putExtra("shopLocation", shopLocation);
                            intent.putExtra("bidPrice", bidPrice);
                            intent.putExtra("selectedCategory", selectedCategory);
                            intent.putExtra("selectedRadioButton", selectedRadioButton);
                            startActivity(intent);
//                            Intent intent = new Intent(getActivity(), OTPAuth.class);
//                            intent.putExtra("buyerPhone", phoneNumber);
//                            intent.putExtra("selectedCategory", selectedCategory);
//                            intent.putExtra("selectedRadioButton", selectedRadioButton);
//                            intent.putExtra("fromLoginActivity", whichActivity);
//                            startActivity(intent);
//                            Intent intent = new Intent();
//                            intent.setClass(getActivity(), OTPAuth.class);
//                            intent.putExtra("mobileNumber", mobileNumber);
//                            intent.putExtra("headName", familyHeadName);
//                            intent.putExtra("gender", gender);
//                            intent.putExtra("email", userEmail);
//                            intent.putExtra("address1", address1);
//                            intent.putExtra("address2", address2);
//                            intent.putExtra("zipCode", zipCode);
//                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        if (dialog != null && dialog.isShowing())
                            dialog.dismiss();
                        Toast.makeText(getActivity(), "Firebase error occurred", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
    }
}
