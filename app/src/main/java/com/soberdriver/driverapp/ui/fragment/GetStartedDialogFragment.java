package com.soberdriver.driverapp.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.soberdriver.driverapp.R;

/**
 * Created by roman
 */

public class GetStartedDialogFragment extends DialogFragment {

    private GetStartedDialogListener mGetStartedDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.dialog_get_started_skin, null, false);

        LinearLayout inOfficialBtn = (LinearLayout) view.findViewById(
                R.id.get_started_dialog_in_official);
        LinearLayout noOfficialBtn = (LinearLayout) view.findViewById(
                R.id.get_started_dialog_no_official);

        inOfficialBtn.setOnClickListener(view1 -> {
            if (mGetStartedDialogListener != null) {
                mGetStartedDialogListener.inOfficial();
            }
        });
        noOfficialBtn.setOnClickListener(view12 -> {
            if (mGetStartedDialogListener != null) {
                mGetStartedDialogListener.noOfficial();
            }
        });
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }

    public void setGetStartedDialogListener(
            GetStartedDialogListener getStartedDialogListener) {
        mGetStartedDialogListener = getStartedDialogListener;
    }

    public interface GetStartedDialogListener {
        void inOfficial();

        void noOfficial();
    }
}