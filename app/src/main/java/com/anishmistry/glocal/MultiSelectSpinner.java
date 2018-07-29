package com.anishmistry.glocal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.SpinnerAdapter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MultiSelectSpinner extends AppCompatSpinner implements DialogInterface.OnMultiChoiceClickListener {
    public interface OnMultipleItemsSelectedListener{
        void selectedIndices(List<Integer> indices);
        void selectedStrings(List<String> strings);
    }
    private OnMultipleItemsSelectedListener listener;

    String[] items = null;
    boolean[] mSelection = null;
    boolean[] mSelectionAtStart = null;
    String itemsAtStart = null;
    Context c;
    ArrayAdapter<String> simpleAdapter;
    private boolean hasNone = false;

    public MultiSelectSpinner(Context context) {
        super(context);
        c = context;
        simpleAdapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item);
        super.setAdapter(simpleAdapter);
    }

    public MultiSelectSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        simpleAdapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item);
        super.setAdapter(simpleAdapter);
    }

    public void setListener(OnMultipleItemsSelectedListener listener){
        this.listener = listener;
    }

    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (mSelection != null && which < mSelection.length) {
            if(hasNone) {
                if (which == 0 && isChecked && mSelection.length > 1) {
                    for (int i = 1; i < mSelection.length; i++) {
                        mSelection[i] = false;
                        ((AlertDialog) dialog).getListView().setItemChecked(i, false);
                    }
                } else if (which > 0 && mSelection[0] && isChecked) {
                    mSelection[0] = false;
                    ((AlertDialog) dialog).getListView().setItemChecked(0, false);
                }
            }
            mSelection[which] = isChecked;
            simpleAdapter.clear();
            simpleAdapter.add(buildSelectedItemString());
        } else {
            throw new IllegalArgumentException(
                    "Argument 'which' is out of bounds.");
        }
    }

    @Override
    public boolean performClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Please select!");
        builder.setMultiChoiceItems(items, mSelection, this);
        itemsAtStart = getSelectedItemsAsString();
//        builder.setNeutralButton("Clear", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                setSelection(0);
//            }
//        });
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.arraycopy(mSelection, 0, mSelectionAtStart, 0, mSelection.length);
                listener.selectedIndices(getSelectedIndices());
                listener.selectedStrings(getSelectedStrings());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                simpleAdapter.clear();
                simpleAdapter.add(itemsAtStart);
                System.arraycopy(mSelectionAtStart, 0, mSelection, 0, mSelectionAtStart.length);
            }
        });
        builder.show();
        return true;
    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        throw new RuntimeException(
                "setAdapter is not supported by MultiSelectSpinner.");
    }

    public void setItems(String[] items) {
        this.items = items;
        mSelection = new boolean[this.items.length];
        mSelectionAtStart = new boolean[this.items.length];
        simpleAdapter.clear();
        simpleAdapter.add(this.items[0]);
        Arrays.fill(mSelection, false);
        mSelection[0] = true;
        mSelectionAtStart[0] = true;
    }

    public void setItems(List<String> items) {
        this.items = items.toArray(new String[items.size()]);
        mSelection = new boolean[this.items.length];
        mSelectionAtStart  = new boolean[this.items.length];
        simpleAdapter.clear();
        simpleAdapter.add(this.items[0]);
        Arrays.fill(mSelection, false);
        mSelection[0] = true;
    }

    public void setSelection(String[] selection) {
        for (int i = 0; i < mSelection.length; i++) {
            mSelection[i] = false;
            mSelectionAtStart[i] = false;
        }
        for (String cell : selection) {
            for (int j = 0; j < items.length; ++j) {
                if (items[j].equals(cell)) {
                    mSelection[j] = true;
                    mSelectionAtStart[j] = true;
                }
            }
        }
        simpleAdapter.clear();
        simpleAdapter.add(buildSelectedItemString());
    }

    public void setSelection(List<String> selection) {
        for (int i = 0; i < mSelection.length; i++) {
            mSelection[i] = false;
            mSelectionAtStart[i] = false;
        }
        for (String sel : selection) {
            for (int j = 0; j < items.length; ++j) {
                if (items[j].equals(sel)) {
                    mSelection[j] = true;
                    mSelectionAtStart[j] = true;
                }
            }
        }
        simpleAdapter.clear();
        simpleAdapter.add(buildSelectedItemString());
    }


    public void setSelection(int index) {
        for (int i = 0; i < mSelection.length; i++) {
            mSelection[i] = false;
            mSelectionAtStart[i] = false;
        }
        if (index >= 0 && index < mSelection.length) {
            mSelection[index] = true;
            mSelectionAtStart[index] = true;
        } else {
            throw new IllegalArgumentException("Index " + index
                    + " is out of bounds.");
        }
        simpleAdapter.clear();
        simpleAdapter.add(buildSelectedItemString());
    }

    public void setSelection(int[] selectedIndices) {
        for (int i = 0; i < mSelection.length; i++) {
            mSelection[i] = false;
            mSelectionAtStart[i] = false;
        }
        for (int index : selectedIndices) {
            if (index >= 0 && index < mSelection.length) {
                mSelection[index] = true;
                mSelectionAtStart[index] = true;
            } else {
                throw new IllegalArgumentException("Index " + index
                        + " is out of bounds.");
            }
        }
        simpleAdapter.clear();
        simpleAdapter.add(buildSelectedItemString());
    }

    public List<String> getSelectedStrings() {
        List<String> selection = new LinkedList<>();
        for (int i = 0; i < items.length; ++i) {
            if (mSelection[i]) {
                selection.add(items[i]);
            }
        }
        return selection;
    }

    public List<Integer> getSelectedIndices() {
        List<Integer> selection = new LinkedList<>();
        for (int i = 0; i < items.length; ++i) {
            if (mSelection[i]) {
                selection.add(i);
            }
        }
        return selection;
    }

    private String buildSelectedItemString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;

        for (int i = 0; i < items.length; ++i) {
            if (mSelection[i]) {
                if (foundOne) {
                    sb.append(", ");
                }
                foundOne = true;

                sb.append(items[i]);
            }
        }
        return sb.toString();
    }

    public String getSelectedItemsAsString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;

        for (int i = 0; i < items.length; ++i) {
            if (mSelection[i]) {
                if (foundOne) {
                    sb.append(", ");
                }
                foundOne = true;
                sb.append(items[i]);
            }
        }
        return sb.toString();
    }

    public void hasNoneOption(boolean val){
        hasNone = val;
    }
}

