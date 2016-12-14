package com.soberdriver.driverapp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.OrderStartedPresenter;
import com.soberdriver.driverapp.presentation.view.OrderStartedView;
import com.soberdriver.driverapp.ui.activity.MainDriverActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderStartedFragment extends AppBaseFragment implements OrderStartedView {
    public static final String TAG = "OrderStartedFragment";
    @InjectPresenter
    OrderStartedPresenter mOrderStartedPresenter;
    @BindView(R.id.layout_driver_open_profile_btn)
    FrameLayout mOpenProfileBtn;
    @BindView(R.id.order_started_navigator_btn)
    AppCompatButton mOrderStartedNavigatorBtn;
    @BindView(R.id.order_started_complete_btn)
    AppCompatButton mOrderStartedCompleteBtn;

    public static OrderStartedFragment newInstance() {
        OrderStartedFragment fragment = new OrderStartedFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_started, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick({R.id.layout_driver_open_profile_btn, R.id.order_started_navigator_btn,
            R.id.order_started_complete_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_driver_open_profile_btn:
                break;
            case R.id.order_started_navigator_btn:
                break;
            case R.id.order_started_complete_btn:
                ((MainDriverActivity) getActivity()).startPaymentFragment();
                break;
        }
    }
}
