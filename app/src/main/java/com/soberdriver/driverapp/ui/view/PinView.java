package com.soberdriver.driverapp.ui.view;


import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.soberdriver.driverapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by zest .
 */

public class PinView extends FrameLayout {

    @BindView(R.id.pin_view_first_edit_text)
    AppCompatEditText mPinViewFirstEditText;
    @BindView(R.id.pin_view_second_edit_text)
    AppCompatEditText mPinViewSecondEditText;
    @BindView(R.id.pin_view_third_edit_text)
    AppCompatEditText mPinViewThirdEditText;
    @BindView(R.id.pin_view_fourth_edit_text)
    AppCompatEditText mPinViewFourthEditText;
    private Unbinder unbinder;
    private String pin = "";
    private PinViewCallback mPinViewCallback;

    public PinView(Context context) {
        super(context);
        initView();
    }

    public PinView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PinView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PinView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.pin_view_layout, this, false);
        addView(view);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();
    }

    public void inputCod(String code) {
        if (mPinViewFirstEditText.getText().toString().isEmpty()) {
            mPinViewFirstEditText.setText(code);
            pin += code;
        } else if (mPinViewSecondEditText.getText().toString().isEmpty()) {
            mPinViewSecondEditText.setText(code);
            pin += code;
        } else if (mPinViewThirdEditText.getText().toString().isEmpty()) {
            mPinViewThirdEditText.setText(code);
            pin += code;
        } else if (mPinViewFourthEditText.getText().toString().isEmpty()) {
            mPinViewFourthEditText.setText(code);
            pin += code;
            if (mPinViewCallback != null) {
                mPinViewCallback.codeIsEntered(pin);
                pin = "";
            }
        }
    }

    public void removeCod() {
        if (!mPinViewFourthEditText.getText().toString().isEmpty()) {
            mPinViewFourthEditText.setText("");
        } else if (!mPinViewThirdEditText.getText().toString().isEmpty()) {
            mPinViewThirdEditText.setText("");
        } else if (!mPinViewSecondEditText.getText().toString().isEmpty()) {
            mPinViewSecondEditText.setText("");
        } else if (!mPinViewFirstEditText.getText().toString().isEmpty()) {
            mPinViewFirstEditText.setText("");
        }
        if (pin.length() > 0) {
            pin = pin.substring(0, pin.length() - 1);
        }
    }

    public void clear() {
        mPinViewFirstEditText.setText("");
        mPinViewSecondEditText.setText("");
        mPinViewThirdEditText.setText("");
        mPinViewFourthEditText.setText("");
    }

    public void setCodeCallback(PinViewCallback pinViewCallback) {
        mPinViewCallback = pinViewCallback;
    }

    public interface PinViewCallback {
        void codeIsEntered(String cod);
    }

    public String getPin() {
        return pin;
    }
}
