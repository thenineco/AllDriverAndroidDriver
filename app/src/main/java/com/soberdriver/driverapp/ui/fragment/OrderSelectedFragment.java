package com.soberdriver.driverapp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.OrderSelectedPresenter;
import com.soberdriver.driverapp.presentation.view.OrderSelectedView;
import com.soberdriver.driverapp.ui.activity.MainDriverActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderSelectedFragment extends AppBaseFragment implements OrderSelectedView {
    public static final String TAG = "OrderSelectedFragment";
    @InjectPresenter
    OrderSelectedPresenter mOrderSelectedPresenter;
    @BindView(R.id.layout_driver_open_profile_btn)
    FrameLayout mClientProfileBtn;
    @BindView(R.id.order_selected_route_btn)
    AppCompatButton mOrderSelectedRouteBtn;
    @BindView(R.id.order_selected_driver_here_btn)
    AppCompatButton mOrderSelectedDriverHereBtn;
    private DriverHereDialogFragment driverHereDialogFragment;

    public static OrderSelectedFragment newInstance() {
        OrderSelectedFragment fragment = new OrderSelectedFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_selected, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDialogFragment();
    }

    private void setDialogFragment() {
        driverHereDialogFragment = new DriverHereDialogFragment();
        driverHereDialogFragment.setOnClickListener(view -> {
            switch (view.getId()) {
                case R.id.dialog_driver_here_yes_btn:
                    ((MainDriverActivity) getActivity()).startOrderStartedFragment();
                    driverHereDialogFragment.dismiss();
                    break;
                case R.id.dialog_driver_here_no_btn:
                    driverHereDialogFragment.dismiss();
                    break;
            }
        });
    }

    @OnClick({R.id.layout_driver_open_profile_btn, R.id.order_selected_route_btn,
            R.id.order_selected_driver_here_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_driver_open_profile_btn:
                break;
            case R.id.order_selected_route_btn:
                break;
            case R.id.order_selected_driver_here_btn:
                checkClick();
                break;
        }
    }

    private void checkClick() {
        driverHereDialogFragment.show(getActivity().getSupportFragmentManager(),"Driver here dialog");
    }
}
