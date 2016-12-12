package com.soberdriver.driverapp.ui.activity.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.registration.InputPhoneNumberPresenter;
import com.soberdriver.driverapp.presentation.view.registration.InputPhoneNumberView;
import com.soberdriver.driverapp.ui.activity.AppBaseActivity;
import com.soberdriver.driverapp.ui.view.SelectablePhoneEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputPhoneNumberActivity extends AppBaseActivity implements InputPhoneNumberView {
    public static final String TAG = "InputPhoneNumberActivity";
    @InjectPresenter
    InputPhoneNumberPresenter mInputPhoneNumberPresenter;
    @BindView(R.id.input_phone_number_user_phone_number_edit_text)
    SelectablePhoneEditText mUserPhoneNumberEditText;
    @BindView(R.id.input_phone_number_send_user_data_btn)
    AppCompatButton mSendUserDataBtn;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, InputPhoneNumberActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_phone_number);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.input_phone_number_send_user_data_btn)
    public void onClick() {
        startActivity(InputPinCodeActivity.getIntent(this));
    }
}
