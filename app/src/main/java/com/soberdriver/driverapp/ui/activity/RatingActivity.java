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
import com.soberdriver.driverapp.presentation.presenter.RatingPresenter;
import com.soberdriver.driverapp.presentation.view.RatingView;
import com.soberdriver.driverapp.ui.view.AppCustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RatingActivity extends AppBaseActivity implements RatingView {
    public static final String TAG = "RatingActivity";
    @InjectPresenter
    RatingPresenter mRatingPresenter;
    @BindView(R.id.rating_toolbar)
    AppCustomToolbar mToolbar;
    @BindView(R.id.rating_back_btn)
    AppCompatImageView mBackBtn;
    @BindView(R.id.rating_show_leader_board_btn)
    AppCompatButton mShowLeaderBoardBtn;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, RatingActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ButterKnife.bind(this);
        setToolbar();
    }

    private void setToolbar() {
        mToolbar.setToolbarTitle("Рейтинг");
        Toolbar toolbar = mToolbar.getToolbar();
        setSupportActionBar(toolbar);
    }


    @OnClick({R.id.rating_back_btn, R.id.rating_show_leader_board_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rating_back_btn:
                break;
            case R.id.rating_show_leader_board_btn:
                break;
        }
    }
}
