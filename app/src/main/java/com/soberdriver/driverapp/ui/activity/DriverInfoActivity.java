package com.soberdriver.driverapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.DriverInfoPresenter;
import com.soberdriver.driverapp.presentation.view.DriverInfoView;
import com.soberdriver.driverapp.ui.view.AppCustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverInfoActivity extends AppBaseActivity implements DriverInfoView {
    public static final String TAG = "DriverInfoActivity";
    @InjectPresenter
    DriverInfoPresenter mDriverInfoPresenter;
    @BindView(R.id.driver_info_start_change_info_btn)
    AppCompatButton mStartChangeInfoBtn;
    @BindView(R.id.driver_info_toolbar)
    AppCustomToolbar mToolbar;
    @BindView(R.id.driver_info_back_btn)
    AppCompatImageView mBackBtn;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, DriverInfoActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_info);
        ButterKnife.bind(this);
        setToolbar();
    }

    private void setToolbar() {
        mToolbar.setToolbarTitle("Подробнее о себе");
        Toolbar toolbar = mToolbar.getToolbar();
        setSupportActionBar(toolbar);
    }

    @OnClick({R.id.driver_info_start_change_info_btn, R.id.driver_info_back_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.driver_info_start_change_info_btn:
                break;
            case R.id.driver_info_back_btn:
                finish();
                break;
        }
    }
}
