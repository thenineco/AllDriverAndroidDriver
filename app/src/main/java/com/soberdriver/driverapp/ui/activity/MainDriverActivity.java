package com.soberdriver.driverapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.MainDriverPresenter;
import com.soberdriver.driverapp.presentation.view.MainDriverView;
import com.soberdriver.driverapp.ui.fragment.ActiveOrderFragment;
import com.soberdriver.driverapp.ui.fragment.BeginningOfWorkFragment;
import com.soberdriver.driverapp.ui.fragment.OrderSelectedFragment;
import com.soberdriver.driverapp.ui.fragment.OrderStartedFragment;
import com.soberdriver.driverapp.ui.fragment.PaymentCorrectFragment;
import com.soberdriver.driverapp.ui.fragment.PaymentFragment;
import com.soberdriver.driverapp.ui.view.AppCustomToolbar;
import com.soberdriver.driverapp.utils.DisplayUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainDriverActivity extends AppBaseActivity implements MainDriverView {
    public static final String TAG = "MainDriverActivity";
    @InjectPresenter
    MainDriverPresenter mMainDriverPresenter;
    @BindView(R.id.main_driver_fragment_container)
    FrameLayout mMainDriverFragmentContainer;
    @BindView(R.id.toolbar)
    AppCustomToolbar mToolbar;
    @BindView(R.id.user_menu_user_name_text_view)
    AppCompatTextView mUserMenuUserNameTextView;
    @BindView(R.id.user_menu_user_experience_text_view)
    AppCompatTextView mUserMenuUserExperienceTextView;
    @BindView(R.id.user_menu_user_leader_board_text_view)
    AppCompatTextView mUserMenuUserLiderBoardTextView;
    @BindView(R.id.user_menu_pre_order_text_view)
    AppCompatTextView mUserMenuPreOrderTextView;
    @BindView(R.id.user_menu_mi_orders_text_view)
    AppCompatTextView mUserMenuMiOrdersTextView;
    @BindView(R.id.user_menu_rates_text_view)
    AppCompatTextView mUserMenuRatesTextView;
    @BindView(R.id.user_menu_travel_safety_text_view)
    AppCompatTextView mUserMenuTravelSafetyTextView;
    @BindView(R.id.user_menu_about_company_text_view)
    AppCompatTextView mUserMenuAboutCompanyTextView;
    @BindView(R.id.main_drawer_layout)
    DrawerLayout mMainDrawerLayout;
    private MaterialMenuDrawable materialMenu;
    private boolean menuIsOpen;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, MainDriverActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_driver);
        ButterKnife.bind(this);
        setToolbar();
        setDrawerView();
        startBeginingOfWorkFragment();
//        startActiveOrderFragment();
    }

    @OnClick({R.id.user_menu_user_experience_text_view, R.id.user_menu_user_leader_board_text_view,
            R.id.user_menu_pre_order_text_view, R.id.user_menu_mi_orders_text_view,
            R.id.user_menu_rates_text_view, R.id.user_menu_travel_safety_text_view,
            R.id.user_menu_about_company_text_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_menu_user_experience_text_view:
                break;
            case R.id.user_menu_user_leader_board_text_view:
                startLeaderBordActivity();
                break;
            case R.id.user_menu_pre_order_text_view:
                break;
            case R.id.user_menu_mi_orders_text_view:
                break;
            case R.id.user_menu_rates_text_view:
                break;
            case R.id.user_menu_travel_safety_text_view:
                break;
            case R.id.user_menu_about_company_text_view:
                break;
        }
    }

    private void setDrawerView() {
        ViewGroup.LayoutParams layoutParams = mMainDrawerLayout.getLayoutParams();
        layoutParams.width = DisplayUtils.getDisplayWidth(this);
        mMainDrawerLayout.requestLayout();

        mMainDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                materialMenu.setTransformationOffset(
                        MaterialMenuDrawable.AnimationState.BURGER_ARROW,
                        menuIsOpen ? 2 - slideOffset : slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                menuIsOpen = true;

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                menuIsOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void startLeaderBordActivity() {
        startActivity(LeaderBordActivity.getIntent(this));
    }

    private void setToolbar() {
        Toolbar toolbar = mToolbar.getToolbar();
        setSupportActionBar(toolbar);
        materialMenu = new MaterialMenuDrawable(this, Color.WHITE,
                MaterialMenuDrawable.Stroke.THIN);
        toolbar.setNavigationIcon(materialMenu);
        toolbar.setNavigationOnClickListener(v -> {
            if (menuIsOpen) {
                materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW);
                mMainDrawerLayout.closeDrawers();
            } else {
                materialMenu.animateIconState(MaterialMenuDrawable.IconState.BURGER);
                mMainDrawerLayout.openDrawer(Gravity.LEFT);
            }
            // random state
        });
    }

    public void startActiveOrderFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_driver_fragment_container, ActiveOrderFragment.newInstance())
                .addToBackStack(ActiveOrderFragment.TAG)
                .commit();
        mToolbar.setToolbarTitle("Активный заказ");
    }

    public void startOrderSelectedFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_driver_fragment_container, OrderSelectedFragment.newInstance())
                .addToBackStack(OrderSelectedFragment.TAG)
                .commit();
        mToolbar.setToolbarTitle("Еду на заказ");
    }

    public void startOrderStartedFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_driver_fragment_container, OrderStartedFragment.newInstance())
                .addToBackStack(OrderStartedFragment.TAG)
                .commit();
        mToolbar.setToolbarTitle("На заказе");
    }

    public void startPaymentFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_driver_fragment_container, PaymentFragment.newInstance())
                .addToBackStack(PaymentFragment.TAG)
                .commit();
        mToolbar.setToolbarTitle("Оцените");
    }

    public void startPaymentCorrectFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_driver_fragment_container, PaymentCorrectFragment.newInstance())
                .addToBackStack(PaymentCorrectFragment.TAG)
                .commit();
        mToolbar.setToolbarTitle("Корректировка стоимости");
    }

    public void startBeginingOfWorkFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_driver_fragment_container, BeginningOfWorkFragment.newInstance())
                .addToBackStack(BeginningOfWorkFragment.TAG)
                .commit();
    }
}
