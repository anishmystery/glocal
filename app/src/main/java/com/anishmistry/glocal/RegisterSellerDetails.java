package com.anishmistry.glocal;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterSellerDetails extends Fragment {

    View view;
    Bundle bundle;
    EditText editTextSellerName, editTextSellerPhone, editTextSellerEmail;
    TextView textViewDots1, textViewDots2, textViewDots3;
    private Button buttonNext;
    private boolean validPhone, validEmail;
    private String name, phone, email;

    public RegisterSellerDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_register_seller_details, container, false);
        buttonNext = view.findViewById(R.id.next);
        editTextSellerName = view.findViewById(R.id.sellerName);
        editTextSellerPhone = view.findViewById(R.id.sellerPhone);
        editTextSellerPhone.setText("+91");
        editTextSellerEmail = view.findViewById(R.id.sellerEmail);
        textViewDots1 = view.findViewById(R.id.dots1);
        textViewDots2 = view.findViewById(R.id.dots2);
        textViewDots3 = view.findViewById(R.id.dots3);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (TextUtils.isEmpty(editTextSellerName.getText().toString())) {
//                    editTextSellerName.setError("Seller name required");
//                } else if (TextUtils.isEmpty(editTextSellerPhone.getText().toString())) {
//                    editTextSellerPhone.setError("Seller phone number required");
//                } else if (TextUtils.isEmpty(editTextSellerEmail.getText().toString())) {
//                    editTextSellerEmail.setError("Seller email required");
//                } else {
//                    validPhone = Patterns.PHONE.matcher(editTextSellerPhone.getText().toString().trim()).matches();
//                    validEmail = Patterns.EMAIL_ADDRESS.matcher(editTextSellerEmail.getText().toString().trim()).matches();
//                    if (validPhone == false) {
//                        editTextSellerPhone.setError("Invalid phone number");
//                    } else if (validEmail == false) {
//                        editTextSellerEmail.setError("Invalid email");
//                    } else {
                        name = editTextSellerName.getText().toString().trim();
                        phone = editTextSellerPhone.getText().toString().trim();
                        email = editTextSellerEmail.getText().toString().trim();
                        bundle = new Bundle();
                        bundle.putString("sellerName", name);
                        bundle.putString("sellerPhone", phone);
                        bundle.putString("sellerEmail", email);

                        Fragment fragment;
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragment = new RegisterShopDetails();
                        fragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        textViewDots2.setTextColor(getResources().getColor(R.color.colorAccent));
                    //}
                }

            //}
        });

        return view;
    }
}
