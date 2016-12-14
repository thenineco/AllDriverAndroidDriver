package com.soberdriver.driverapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.view.PasswordChangeView;
import com.soberdriver.driverapp.presentation.presenter.PasswordChangePresenter;


import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.ui.view.AppCustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends AppBaseActivity implements PasswordChangeView {
    public static final String TAG = "ChangePasswordActivity";
    @InjectPresenter
    PasswordChangePresenter mPasswordChangePresenter;

    @BindView(R.id.password_change_back_btn)
    AppCompatImageView mBackBtn;
    @BindView(R.id.password_change_password__confirm_edit_text)
    AppCompatEditText mPasswordConfirmEditText;
    @BindView(R.id.password_change_password_edit_text)
    AppCompatEditText mPasswordEditText;
    @BindView(R.id.password_change_save_btn)
    AppCompatButton mSaveBtn;
    @BindView(R.id.password_change_toolbar)
    AppCustomToolbar mToolbar;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, ChangePasswordActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);
        ButterKnife.bind(this);
        setToolbar();
    }

    private void setToolbar() {
        mToolbar.setToolbarTitle("Смена пароля");
        Toolbar toolbar = mToolbar.getToolbar();
        setSupportActionBar(toolbar);
    }

    @OnClick({R.id.password_change_back_btn, R.id.password_change_save_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.password_change_back_btn:
                finish();
                break;
            case R.id.password_change_save_btn:
                finish();
                break;
        }
    }
}
