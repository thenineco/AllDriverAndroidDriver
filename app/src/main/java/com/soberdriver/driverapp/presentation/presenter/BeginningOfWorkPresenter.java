package com.soberdriver.driverapp.presentation.presenter;


import com.module.network.networkmodule.api_v1.HttpService;
import com.module.network.networkmodule.utils.DriverUtil;
import com.soberdriver.driverapp.DriverApp;
import com.soberdriver.driverapp.presentation.view.BeginningOfWorkView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

@InjectViewState
public class BeginningOfWorkPresenter extends MvpPresenter<BeginningOfWorkView> {

    public void openSession() {
        HttpService.getInstance()
                .openDriverSession(DriverApp.getContext())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    ((DriverApp) DriverApp.getContext()).startSocket(
                            DriverUtil.getDriver(DriverApp.getContext()).getDriverId());
                    getViewState().hideDialogFragment();
                    getViewState().setSessionOpenMode();
                }, throwable -> {
                    throwable.printStackTrace();
                    getViewState().hideDialogFragment();
                });
    }

    public void closeSession() {
        HttpService.getInstance()
                .closeDriverSession(DriverApp.getContext())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    getViewState().hideDialogFragment();
                    getViewState().setSessionCloseMode();
                    ((DriverApp) DriverApp.getContext()).closeSocketConnection();
                }, throwable -> {
                    throwable.printStackTrace();
                    getViewState().hideDialogFragment();
                });
    }
}
