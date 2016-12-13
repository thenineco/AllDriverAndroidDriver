package com.soberdriver.driverapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.view.PasswordChangeView;
import com.soberdriver.driverapp.presentation.presenter.PasswordChangePresenter;


import com.arellomobile.mvp.presenter.InjectPresenter;

public class PasswordChangeActivity extends AppBaseActivity implements PasswordChangeView {
    public static final String TAG = "PasswordChangeActivity";
    @InjectPresenter
    PasswordChangePresenter mPasswordChangePresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, PasswordChangeActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);
    }
}
