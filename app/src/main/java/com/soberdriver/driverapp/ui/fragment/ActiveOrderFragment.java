package com.soberdriver.driverapp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.ActiveOrderPresenter;
import com.soberdriver.driverapp.presentation.view.ActiveOrderView;
import com.soberdriver.driverapp.ui.activity.MainDriverActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActiveOrderFragment extends AppBaseFragment implements ActiveOrderView {
    public static final String TAG = "ActiveOrderFragment";
    @InjectPresenter
    ActiveOrderPresenter mActiveOrderPresenter;
    @BindView(R.id.active_order_get_order_btn)
    AppCompatButton mrGetOrderBtn;
    @BindView(R.id.active_order_cancel_order_btn)
    AppCompatButton mCancelOrderBtn;

    public static ActiveOrderFragment newInstance() {
        ActiveOrderFragment fragment = new ActiveOrderFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active_order, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick({R.id.active_order_get_order_btn, R.id.active_order_cancel_order_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.active_order_get_order_btn:
                ((MainDriverActivity) getActivity()).startOrderSelectedFragment();
                break;
            case R.id.active_order_cancel_order_btn:
                getActivity().onBackPressed();
                break;
        }
    }
}
