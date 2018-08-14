package com.anishmistry.glocal;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterShopDetails extends Fragment {
    View view;
    Bundle bundle;
    EditText editTextShopName, editTextShopLocation, editTextBidPrice;
    TextView textViewDots1, textViewDots2, textViewDots3;
    private Button buttonNext, buttonBack;
    private String source, name, phone, email, shopName, shopLocation, bidPrice;

    public RegisterShopDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register_shop_details, container, false);
        buttonBack = view.findViewById(R.id.back);
        buttonNext = view.findViewById(R.id.next);
        textViewDots1 = view.findViewById(R.id.dots1);
        textViewDots2 = view.findViewById(R.id.dots2);
        textViewDots3 = view.findViewById(R.id.dots3);
        editTextShopName = view.findViewById(R.id.shopName);
        editTextShopLocation = view.findViewById(R.id.shopLocation);
        editTextBidPrice = view.findViewById(R.id.bidPrice);

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setCountry("IND")
                .build();

        PlaceAutocompleteFragment sourceAutoCompleteFragment = (PlaceAutocompleteFragment) getChildFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        sourceAutoCompleteFragment.setFilter(typeFilter);

        sourceAutoCompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                source = place.getLatLng().toString();
                // TODO: Get info about the selected place.
                Log.i("SCHEDULE", "Source" + source);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    editTextShopName.setText(place.getName());
                    editTextShopLocation.setText(place.getAddress());
                    shopName = place.getName().toString();
                    shopLocation = place.getAddress().toString();

                }
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("MAPS", "An error occurred: " + status);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bidPrice = editTextBidPrice.getText().toString();

                name = getArguments().getString("sellerName");
                phone = getArguments().getString("sellerPhone");
                email = getArguments().getString("sellerEmail");
                bundle = new Bundle();
                bundle.putString("sellerName", name);
                bundle.putString("sellerPhone", phone);
                bundle.putString("sellerEmail", email);
                bundle.putString("shopName", shopName);
                bundle.putString("shopLocation", shopLocation);
                bundle.putString("bidPrice", bidPrice);
                Fragment fragment;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragment = new RegisterCategoryDetails();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                textViewDots3.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragment = new RegisterSellerDetails();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                textViewDots1.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });
        return view;
    }
}
