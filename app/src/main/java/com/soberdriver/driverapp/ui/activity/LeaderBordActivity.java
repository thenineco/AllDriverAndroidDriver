package com.soberdriver.driverapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.LiaderBordPresenter;
import com.soberdriver.driverapp.presentation.view.LiaderBordView;
import com.soberdriver.driverapp.ui.view.AppCustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeaderBordActivity extends AppBaseActivity implements LiaderBordView {
    public static final String TAG = "LeaderBordActivity";
    @InjectPresenter
    LiaderBordPresenter mLiaderBordPresenter;
    @BindView(R.id.leader_bord_toolbar)
    AppCustomToolbar mToolbar;
    @BindView(R.id.leader_bord_back_btn)
    AppCompatImageView mBackBtn;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, LeaderBordActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liader_bord);
        ButterKnife.bind(this);
        setToolbar();
    }

    private void setToolbar() {
        mToolbar.setToolbarTitle("Лидер Борд");
        Toolbar toolbar = mToolbar.getToolbar();
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.leader_bord_back_btn)
    public void onClick() {
        finish();
    }
}
