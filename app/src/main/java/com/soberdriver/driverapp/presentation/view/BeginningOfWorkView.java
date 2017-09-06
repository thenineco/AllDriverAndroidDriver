package com.soberdriver.driverapp.presentation.view;

import com.arellomobile.mvp.MvpView;

public interface BeginningOfWorkView extends MvpView {

    void hideDialogFragment();

    void setSessionCloseMode();

    void setSessionOpenMode();
}
