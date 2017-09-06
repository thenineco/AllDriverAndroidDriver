package com.soberdriver.driverapp.presentation.presenter.registration;


import com.module.network.networkmodule.api_v1.HttpService;
import com.module.network.networkmodule.models.driver.Driver;
import com.module.network.networkmodule.utils.DriverUtil;
import com.module.network.networkmodule.utils.GsonUtil;
import com.soberdriver.driverapp.DriverApp;
import com.soberdriver.driverapp.presentation.view.registration.InputUserInfoView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

@InjectViewState
public class InputUserInfoPresenter extends MvpPresenter<InputUserInfoView> {

    public void saveUserInfo(Driver driver) {
        HttpService.getInstance()
                .createNewDriver(driver)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            Driver savedDriver = GsonUtil.getGson().fromJson(responseBody.string(),
                                    Driver.class);
                            savedDriver = savedDriver.getDriver();
                            DriverUtil.saveDriver(DriverApp.getContext(), savedDriver);
                            getViewState().finishUserRegistration();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, Throwable::printStackTrace);
    }
}
