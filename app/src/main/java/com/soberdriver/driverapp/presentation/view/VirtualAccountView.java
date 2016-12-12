package com.soberdriver.driverapp.presentation.view;

import com.arellomobile.mvp.MvpView;

public interface VirtualAccountView extends MvpView {

    void startFillUpFragment();

    void startFillUpFinishFragment(String money);
}
