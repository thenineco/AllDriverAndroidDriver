package com.soberdriver.driverapp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.FillUpMoneyPresenter;
import com.soberdriver.driverapp.presentation.view.FillUpMoneyView;
import com.soberdriver.driverapp.ui.activity.VirtualAccountActivity;
import com.soberdriver.driverapp.ui.view.SelectableEditText;
import com.soberdriver.driverapp.ui.view.SelectablePhoneEditText;
import com.soberdriver.driverapp.utils.DisplayUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoneyFillUpFragment extends AppBaseFragment implements FillUpMoneyView {
    public static final String TAG = "MoneyFillUpFragment";
    @InjectPresenter
    FillUpMoneyPresenter mFillUpMoneyPresenter;
    @BindView(R.id.fill_up_money_edit_text)
    AppCompatEditText mFillUpMoneyEditText;
    @BindView(R.id.fill_up_bank_card_mode_radio_button)
    AppCompatRadioButton mFillUpBankCardModeRadioButton;
    @BindView(R.id.fill_up_phone_number_mode_radio_button)
    AppCompatRadioButton mFillUpPhoneNumberModeRadioButton;
    @BindView(R.id.add_new_card_card_number_edit_text)
    SelectableEditText mAddNewCardCardNumberEditText;
    @BindView(R.id.add_new_card_actual_time_edit_text)
    SelectablePhoneEditText mAddNewCardActualTimeEditText;
    @BindView(R.id.add_new_card_security_cod_edit_text)
    SelectableEditText mAddNewCardSecurityCodEditText;
    @BindView(R.id.fill_up_bank_card_mode_container)
    LinearLayout mFillUpBankCardModeContainer;
    @BindView(R.id.input_phone_number_user_phone_number_edit_text)
    SelectablePhoneEditText mInputPhoneNumberUserPhoneNumberEditText;
    @BindView(R.id.fill_up_phone_number_card_mode_container)
    LinearLayout mFillUpPhoneNumberCardModeContainer;
    @BindView(R.id.fill_up_confirm_btn)
    AppCompatButton mFillUpConfirmBtn;
    @BindView(R.id.fill_up_scroll_view)
    ScrollView mFillUpScrollView;
    private boolean keyboardListenersAttached;

    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener =
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (!mFillUpMoneyEditText.isFocused()) {
                        try {
                            mFillUpScrollView.smoothScrollBy(0,
                                    DisplayUtils.getDisplayHeight(getContext()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            };


    public static MoneyFillUpFragment newInstance() {
        MoneyFillUpFragment fragment = new MoneyFillUpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fill_up_money, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBankCardMode();
        setScrollViewParams();
        attachKeyboardListeners();
        setViewParams();
    }

    private void setViewParams() {

    }

    private void setScrollViewParams() {
        mFillUpScrollView.setSmoothScrollingEnabled(true);
        mFillUpScrollView.setVerticalScrollBarEnabled(false);
    }

    void attachKeyboardListeners() {
        if (keyboardListenersAttached) {
            return;
        }
        mFillUpScrollView.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);

        keyboardListenersAttached = true;
    }

    private void setBankCardMode() {
        mFillUpPhoneNumberModeRadioButton.setChecked(false);
        mFillUpBankCardModeRadioButton.setChecked(true);
        mFillUpPhoneNumberCardModeContainer.setVisibility(View.GONE);
        mFillUpBankCardModeContainer.setVisibility(View.VISIBLE);
    }

    private void setPhoneNumberMode() {
        mFillUpBankCardModeRadioButton.setChecked(false);
        mFillUpPhoneNumberModeRadioButton.setChecked(true);
        mFillUpBankCardModeContainer.setVisibility(View.GONE);
        mFillUpPhoneNumberCardModeContainer.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.fill_up_bank_card_mode_radio_button,
            R.id.fill_up_phone_number_mode_radio_button, R.id.fill_up_confirm_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fill_up_bank_card_mode_radio_button:
                setBankCardMode();
                break;
            case R.id.fill_up_phone_number_mode_radio_button:
                setPhoneNumberMode();
                break;
            case R.id.fill_up_confirm_btn:
                String money = mFillUpMoneyEditText.getText().toString();
                ((VirtualAccountActivity) getActivity()).startFillUpFinishFragment(money);
                break;
        }
    }
}
