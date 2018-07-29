package com.anishmistry.glocal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterShopDetails extends Fragment {

    View view;
    Bundle bundle;
    EditText editTextShopName, editTextShopLocation, editTextBidPrice;
    TextView textViewDots1, textViewDots2, textViewDots3;
    private Button buttonNext, buttonBack;

    public RegisterShopDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register_shop_details, container, false);
        buttonBack = view.findViewById(R.id.back);
        buttonNext = view.findViewById(R.id.next);
        textViewDots1 = view.findViewById(R.id.dots1);
        textViewDots2 = view.findViewById(R.id.dots2);
        textViewDots3 = view.findViewById(R.id.dots3);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new RegisterCategoryDetails();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.commit();
                textViewDots3.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new RegisterSellerDetails();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.commit();
                textViewDots1.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });
        return view;
    }
}
