package com.soberdriver.driverapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.view.UserProfileView;
import com.soberdriver.driverapp.presentation.presenter.UserProfilePresenter;


import com.arellomobile.mvp.presenter.InjectPresenter;

public class UserProfileActivity extends AppBaseActivity implements UserProfileView {
    public static final String TAG = "UserProfileActivity";
    @InjectPresenter
    UserProfilePresenter mUserProfilePresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, UserProfileActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }
}
