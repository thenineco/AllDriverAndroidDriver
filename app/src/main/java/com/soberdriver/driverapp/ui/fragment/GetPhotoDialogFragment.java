package com.soberdriver.driverapp.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.soberdriver.driverapp.R;

/**
 * Created by zest
 */

public class GetPhotoDialogFragment extends DialogFragment {
    private View.OnClickListener mOnClickListener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.dialog_get_image, null, false);

        AppCompatButton createPhotoBtn = (AppCompatButton) view.findViewById(
                R.id.dialog_create_photo);
        AppCompatButton getImageFromLibraryBtn = (AppCompatButton) view.findViewById(
                R.id.dialog_get_from_library);
        AppCompatButton cancelBtn = (AppCompatButton) view.findViewById(
                R.id.dialog_cancel);

        createPhotoBtn.setOnClickListener(mOnClickListener);
        getImageFromLibraryBtn.setOnClickListener(mOnClickListener);
        cancelBtn.setOnClickListener(mOnClickListener);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }

    @Override
    public void onStart() {
        super.onStart();

        // Less dimmed background; see http://stackoverflow.com/q/13822842/56285
        Window window = getDialog().getWindow();
        assert window != null;
        WindowManager.LayoutParams params = window.getAttributes();
        params.dimAmount = 0.2f; // dim only a little bit
        params.gravity = Gravity.BOTTOM | Gravity.LEFT;
        window.setAttributes(params);
        // Transparent background; see http://stackoverflow.com/q/15007272/56285
        // (Needed to make dialog's alpha shadow look good)
    }

    public void setClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }
}