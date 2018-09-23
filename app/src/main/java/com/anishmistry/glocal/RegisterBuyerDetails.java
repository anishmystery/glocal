package com.anishmistry.glocal;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterBuyerDetails extends Fragment {

    Button buttonSignup;
    EditText editTextBuyerName, editTextBuyerPhone, editTextBuyerEmail, editTextBuyerDOB;
    RadioGroup radioGroupGender;
    RadioButton radioButtonGender;
    DatePickerDialog datePickerDialog;
    ProgressDialog dialog;
    private int selectedRadioButtonId;
    View view;
    String buyer_name, buyer_phone, buyer_email, buyer_gender, buyer_dob;
    public RegisterBuyerDetails() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register_buyer_details, container, false);
        editTextBuyerName = view.findViewById(R.id.buyerName);
        editTextBuyerEmail = view.findViewById(R.id.buyerEmail);
        radioGroupGender = view.findViewById(R.id.radioGender);

        editTextBuyerPhone = view.findViewById(R.id.buyerPhone);
        editTextBuyerPhone.setText("+91");

        editTextBuyerDOB = view.findViewById(R.id.buyerDOB);
        editTextBuyerDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editTextBuyerDOB.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        buttonSignup = view.findViewById(R.id.signup);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedRadioButton;
                selectedRadioButtonId = radioGroupGender.getCheckedRadioButtonId();
                radioButtonGender = view.findViewById(selectedRadioButtonId);
                selectedRadioButton = radioButtonGender.getText().toString();
                buyer_name = editTextBuyerName.getText().toString().trim();
                buyer_phone = editTextBuyerPhone.getText().toString().trim();
                buyer_email = editTextBuyerEmail.getText().toString().trim();
                buyer_gender = selectedRadioButton;
                buyer_dob = editTextBuyerDOB.getText().toString().trim();

                checkUserExists(buyer_name, buyer_phone, buyer_email, buyer_gender, buyer_dob, false);
            }
        });

        return view;
    }

    public void checkUserExists(final String buyerName, final String buyerPhone, final String buyerEmail, final String buyerGender,
                                final String buyerDOB, final boolean whichActivity) {
        dialog = ProgressDialog.show(getActivity(), "Registration", "Creating User", true);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("users").child("buyers")
                .orderByChild("buyerPhone")
                .equalTo(buyerPhone)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            editTextBuyerPhone.setError("Account with this phone number already exists!");
                            if (dialog != null && dialog.isShowing())
                                dialog.dismiss();
                            return;
                        } else {
                            //Toast.makeText(getApplicationContext(), "Creating new user", Toast.LENGTH_SHORT).show();
                            if (dialog != null && dialog.isShowing())
                                dialog.dismiss();
                            Intent intent = new Intent(getActivity(), OTPAuth.class);
                            intent.putExtra("buyerName", buyerName);
                            intent.putExtra("buyerPhone", buyerPhone);
                            intent.putExtra("buyerEmail", buyerEmail);
                            intent.putExtra("buyerDOB", buyerDOB);
                            intent.putExtra("buyerGender", buyerGender);
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
