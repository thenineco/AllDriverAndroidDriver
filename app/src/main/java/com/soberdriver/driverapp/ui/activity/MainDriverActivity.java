package com.soberdriver.driverapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.MainDriverPresenter;
import com.soberdriver.driverapp.presentation.view.MainDriverView;

public class MainDriverActivity extends AppBaseActivity implements MainDriverView {
    public static final String TAG = "MainDriverActivity";
    @InjectPresenter
    MainDriverPresenter mMainDriverPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, MainDriverActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_driver);
    }
}
