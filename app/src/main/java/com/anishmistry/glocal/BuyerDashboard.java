package com.anishmistry.glocal;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnSelectClickListener;

import java.util.ArrayList;

public class BuyerDashboard extends AppCompatActivity {

    Button buttonNext;
    ArrayList selectedChips;
    Chip chipAppliances, chipBooks, chipClothing, chipElectronics, chipExcerciseFitness, chipHomeFurniture, chipStationary, chipToys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_dashboard);

        selectedChips = new ArrayList();
        buttonNext = findViewById(R.id.buttonNext);
        chipAppliances = findViewById(R.id.chipAppliances);
        chipBooks = findViewById(R.id.chipBooks);
        chipClothing = findViewById(R.id.chipClothing);
        chipElectronics = findViewById(R.id.chipElectronics);
        chipExcerciseFitness = findViewById(R.id.chipExcerciseFitness);
        chipHomeFurniture = findViewById(R.id.chipHomeFurniture);
        chipStationary = findViewById(R.id.chipStationary);
        chipToys = findViewById(R.id.chipToys);

        chipAppliances.setOnSelectClickListener(new OnSelectClickListener() {
            @Override
            public void onSelectClick(View v, boolean selected) {
                selectedChips.add(chipAppliances.getChipText().toString());
            }
        });

        chipBooks.setOnSelectClickListener(new OnSelectClickListener() {
            @Override
            public void onSelectClick(View v, boolean selected) {
                selectedChips.add(chipBooks.getChipText().toString());
            }
        });

        chipClothing.setOnSelectClickListener(new OnSelectClickListener() {
            @Override
            public void onSelectClick(View v, boolean selected) {
                selectedChips.add(chipClothing.getChipText().toString());
            }
        });

        chipElectronics.setOnSelectClickListener(new OnSelectClickListener() {
            @Override
            public void onSelectClick(View v, boolean selected) {
                selectedChips.add(chipElectronics.getChipText().toString());
            }
        });

        chipExcerciseFitness.setOnSelectClickListener(new OnSelectClickListener() {
            @Override
            public void onSelectClick(View v, boolean selected) {
                selectedChips.add(chipExcerciseFitness.getChipText().toString());
            }
        });

        chipHomeFurniture.setOnSelectClickListener(new OnSelectClickListener() {
            @Override
            public void onSelectClick(View v, boolean selected) {
                selectedChips.add(chipHomeFurniture.getChipText().toString());
            }
        });

        chipStationary.setOnSelectClickListener(new OnSelectClickListener() {
            @Override
            public void onSelectClick(View v, boolean selected) {
                selectedChips.add(chipStationary.getChipText().toString());
            }
        });

        chipToys.setOnSelectClickListener(new OnSelectClickListener() {
            @Override
            public void onSelectClick(View v, boolean selected) {
                selectedChips.add(chipToys.getChipText().toString());
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedChips.isEmpty()) {
//                    Toast.makeText(BuyerDashboard.this, "Please select a category", Toast.LENGTH_SHORT).show();
                    Snackbar.make(v, "Please select a category", Snackbar.LENGTH_LONG)
                       .setAction("Action", null).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), BuyerSearch.class);
                    intent.putParcelableArrayListExtra("chips", selectedChips);
                    startActivity(intent);
                }
            }
        });
    }
}
