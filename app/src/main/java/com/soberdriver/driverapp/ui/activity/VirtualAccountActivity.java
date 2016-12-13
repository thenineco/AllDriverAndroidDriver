package com.soberdriver.driverapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.VirtualAccountPresenter;
import com.soberdriver.driverapp.presentation.view.VirtualAccountView;
import com.soberdriver.driverapp.ui.fragment.FillUpFinishFragment;
import com.soberdriver.driverapp.ui.fragment.MoneyFillUpFragment;
import com.soberdriver.driverapp.ui.view.AppCustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VirtualAccountActivity extends AppBaseActivity implements VirtualAccountView {
    public static final String TAG = "VirtualAccountActivity";
    public static final String FILL_UP_ONLY = "fill_up_only";

    @InjectPresenter
    VirtualAccountPresenter mVirtualAccountPresenter;
    @BindView(R.id.virtual_account_toolbar)
    AppCustomToolbar mVirtualAccountToolbar;
    @BindView(R.id.virtual_account_back_btn)
    AppCompatImageView mVirtualAccountBackBtn;
    @BindView(R.id.virtual_account_many_text_view)
    AppCompatTextView mVirtualAccountManyTextView;
    @BindView(R.id.virtual_account_fill_up_btn)
    AppCompatButton mVirtualAccountFillUpBtn;
    private boolean fillUpOnly;

    public static Intent getIntent(final Context context, boolean fillUpOnly) {
        Intent intent = new Intent(context, VirtualAccountActivity.class);
        intent.putExtra(FILL_UP_ONLY, fillUpOnly);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_account);
        ButterKnife.bind(this);
        setToolbar();
        fillUpOnly = getIntent().getBooleanExtra(FILL_UP_ONLY, false);
        if (fillUpOnly) {
            startFillUpFragment();
        }
    }

    private void setToolbar() {
        mVirtualAccountToolbar.setToolbarTitle("Виртуальный счет");
        Toolbar toolbar = mVirtualAccountToolbar.getToolbar();
        setSupportActionBar(toolbar);
    }


    @Override
    public void startFillUpFragment() {
        mVirtualAccountBackBtn.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.virtual_account_fragment_container, MoneyFillUpFragment.newInstance())
                .addToBackStack(MoneyFillUpFragment.TAG)
                .commit();
    }

    @Override
    public void startFillUpFinishFragment(String money) {
        mVirtualAccountBackBtn.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.virtual_account_fragment_container,
                        FillUpFinishFragment.newInstance(money))
                .addToBackStack(FillUpFinishFragment.TAG)
                .commit();
    }

    @OnClick({R.id.virtual_account_back_btn, R.id.virtual_account_fill_up_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.virtual_account_back_btn:
                if (!fillUpOnly) {
                    onBackPressed();
                    mVirtualAccountBackBtn.setVisibility(View.GONE);
                } else {
                    finish();
                }
                break;
            case R.id.virtual_account_fill_up_btn:
                startFillUpFragment();
                break;
        }
    }

    public void closeVirtualAccount() {
        if (!fillUpOnly) {
            startActivity(MainDriverActivity.getIntent(this));
            finish();
        } else {
            finish();
        }
    }
}
