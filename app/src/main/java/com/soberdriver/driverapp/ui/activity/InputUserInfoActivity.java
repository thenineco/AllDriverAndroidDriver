package com.soberdriver.driverapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.InputUserInfoPresenter;
import com.soberdriver.driverapp.presentation.view.InputUserInfoView;

public class InputUserInfoActivity extends AppBaseActivity implements InputUserInfoView {
    public static final String TAG = "InputUserInfoActivity";
    @InjectPresenter
    InputUserInfoPresenter mInputUserInfoPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, InputUserInfoActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_user_info);
    }
}
