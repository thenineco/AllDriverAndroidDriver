package com.soberdriver.driverapp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.FillUpFinishPresenter;
import com.soberdriver.driverapp.presentation.view.FillUpFinishView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FillUpFinishFragment extends AppBaseFragment implements FillUpFinishView {

    public static final String TAG = "FillUpFinishFragment";
    public static final String MONEY = "money";

    @InjectPresenter
    FillUpFinishPresenter mFillUpFinishPresenter;

    @BindView(R.id.fill_up_finish_money_text_view)
    AppCompatTextView mFillUpFinishMoneyTextView;
    @BindView(R.id.fill_up_finish_go_to_profile_btn)
    AppCompatButton mFillUpFinishGoToProfileBtn;

    public static FillUpFinishFragment newInstance(String money) {
        FillUpFinishFragment fragment = new FillUpFinishFragment();
        Bundle args = new Bundle();
        args.putString(MONEY, money);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fill_up_finish, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String money = getArguments().getString(MONEY);
        mFillUpFinishMoneyTextView.setText(money);
    }

    @OnClick(R.id.fill_up_finish_go_to_profile_btn)
    public void onClick() {
    }
}
