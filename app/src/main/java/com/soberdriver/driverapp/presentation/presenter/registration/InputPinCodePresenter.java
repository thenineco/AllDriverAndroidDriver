package com.soberdriver.driverapp.presentation.presenter.registration;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.module.network.networkmodule.api_v1.HttpService;
import com.module.network.networkmodule.models.AuthKey;
import com.module.network.networkmodule.models.driver.Driver;
import com.module.network.networkmodule.utils.DriverTokenUtil;
import com.module.network.networkmodule.utils.DriverUtil;
import com.soberdriver.driverapp.DriverApp;
import com.soberdriver.driverapp.presentation.view.registration.InputPinCodeView;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

@InjectViewState
public class InputPinCodePresenter extends MvpPresenter<InputPinCodeView> {

    public void sendPinCode(String cod) {
        Driver driver = DriverUtil.getDriver(DriverApp.getContext());
        HttpService.getInstance()
                .getAuthKey(driver.getPhone(), Integer.valueOf(cod))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authKey -> {
                    DriverTokenUtil.saveToken(DriverApp.getContext(), authKey.getAuthKey());
                    getViewState().startMainApp();
                }, Throwable::printStackTrace);
    }
}
