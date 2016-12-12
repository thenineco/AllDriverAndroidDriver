package com.soberdriver.driverapp.ui.activity.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.registration.InputPinCodePresenter;
import com.soberdriver.driverapp.presentation.view.registration.InputPinCodeView;
import com.soberdriver.driverapp.ui.activity.AppBaseActivity;
import com.soberdriver.driverapp.ui.view.PinView;
import com.soberdriver.driverapp.utils.KeyboardUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputPinCodeActivity extends AppBaseActivity implements InputPinCodeView {
    public static final String TAG = "InputPinCodeActivity";
    @InjectPresenter
    InputPinCodePresenter mInputPinCodePresenter;
    @BindView(R.id.pin_view)
    PinView mPinView;
    @BindView(R.id.pin_code_edit_text)
    AppCompatEditText mPinCodeEditText;
    @BindView(R.id.toolbar_title_text_view)
    AppCompatTextView mToolbarTitleTextView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.input_phone_number_input_container)
    LinearLayout mInputPhoneNumberInputContainer;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, InputPinCodeActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pin_code);
        ButterKnife.bind(this);
        setPinViewParams();
        startInputMode();
    }

    private void startInputMode() {
        KeyboardUtil.openKeyboard(this);
        mPinCodeEditText.requestFocus();
    }

    private void setPinViewParams() {
        mPinView.setCodeCallback(cod -> {
            KeyboardUtil.closeKeyboard(InputPinCodeActivity.this, mPinCodeEditText);
            startActivity(InputUserInfoActivity.getIntent(InputPinCodeActivity.this));
        });

        mPinCodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals("")) {
                    mPinView.inputCod(charSequence.toString());
                    mPinCodeEditText.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mPinCodeEditText.setOnKeyListener((view, keyCode, keyEvent) -> {
            if (keyCode == KeyEvent.KEYCODE_DEL
                    && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                mPinView.removeCod();
            }
            return false;
        });
    }

    @OnClick(R.id.pin_view)
    public void onClick() {
        startInputMode();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        startInputMode();
    }
}
