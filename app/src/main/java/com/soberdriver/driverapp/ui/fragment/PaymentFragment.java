package com.soberdriver.driverapp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.PaymentPresenter;
import com.soberdriver.driverapp.presentation.view.PaymentView;
import com.soberdriver.driverapp.ui.activity.MainDriverActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentFragment extends AppBaseFragment implements PaymentView {
    public static final String TAG = "PaymentFragment";
    @InjectPresenter
    PaymentPresenter mPaymentPresenter;
    @BindView(R.id.payment_correct_btn)
    AppCompatTextView mPaymentCorrectBtn;
    @BindView(R.id.payment_send_btn)
    AppCompatButton mPaymentSendBtn;

    public static PaymentFragment newInstance() {
        PaymentFragment fragment = new PaymentFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick({R.id.payment_correct_btn, R.id.payment_send_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.payment_correct_btn:
                ((MainDriverActivity) getActivity()).startPaymentCorrectFragment();
                break;
            case R.id.payment_send_btn:
                ((MainDriverActivity) getActivity()).startBeginingOfWorkFragment();
                break;
        }
    }
}
