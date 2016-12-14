package com.soberdriver.driverapp.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.soberdriver.driverapp.R;

/**
 * Created by roman
 */

public class GetFinishedDialogFragment extends DialogFragment {

    private View.OnClickListener mOnClickListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.dialog_get_finished_skin, null, false);

        AppCompatTextView yesBtn = (AppCompatTextView) view.findViewById(
                R.id.dialog_get_finished_yes_btn);
        AppCompatTextView noBtn = (AppCompatTextView) view.findViewById(
                R.id.dialog_get_finished_no_btn);

        yesBtn.setOnClickListener(mOnClickListener);
        noBtn.setOnClickListener(mOnClickListener);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }
}