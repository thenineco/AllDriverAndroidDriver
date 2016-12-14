package com.soberdriver.driverapp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.PaymentCorrectPresenter;
import com.soberdriver.driverapp.presentation.view.PaymentCorrectView;
import com.soberdriver.driverapp.utils.DisplayUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentCorrectFragment extends AppBaseFragment implements PaymentCorrectView {
    public static final String TAG = "PaymentCorrectFragment";
    @InjectPresenter
    PaymentCorrectPresenter mPaymentCorrectPresenter;
    @BindView(R.id.payment_correct_scroll_view)
    ScrollView mPaymentCorrectScrollView;
    @BindView(R.id.payment_correct_save_changes_btn)
    AppCompatButton mPaymentCorrectSaveChangesBtn;

    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener =
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mPaymentCorrectScrollView.smoothScrollBy(0,
                            DisplayUtils.getDisplayHeight(getContext()));

                }
            };
    private boolean keyboardListenersAttached;


    public static PaymentCorrectFragment newInstance() {
        PaymentCorrectFragment fragment = new PaymentCorrectFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_correct, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachKeyboardListeners();
        setScrollViewParams();
    }

    private void setScrollViewParams() {
        mPaymentCorrectScrollView.setSmoothScrollingEnabled(true);
        mPaymentCorrectScrollView.setVerticalScrollBarEnabled(false);
    }

    void attachKeyboardListeners() {
        if (keyboardListenersAttached) {
            return;
        }
        mPaymentCorrectScrollView.getViewTreeObserver().addOnGlobalLayoutListener(
                keyboardLayoutListener);

        keyboardListenersAttached = true;
    }


    @OnClick(R.id.payment_correct_save_changes_btn)
    public void onClick() {
        getActivity().onBackPressed();
    }
}
