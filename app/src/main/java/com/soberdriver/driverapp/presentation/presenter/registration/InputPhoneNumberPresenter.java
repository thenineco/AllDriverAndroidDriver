package com.soberdriver.driverapp.presentation.presenter.registration;

import com.module.network.networkmodule.api_v1.HttpService;
import com.module.network.networkmodule.models.User;
import com.module.network.networkmodule.models.driver.Driver;
import com.module.network.networkmodule.utils.DriverUtil;
import com.module.network.networkmodule.utils.GsonUtil;
import com.soberdriver.driverapp.DriverApp;
import com.soberdriver.driverapp.presentation.view.registration.InputPhoneNumberView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.functions.Action1;

@InjectViewState
public class InputPhoneNumberPresenter extends MvpPresenter<InputPhoneNumberView> {

    public void sendUserPhoneNumber(String userPhoneNumber) {
        Driver driver = new Driver();
        driver.setPhone(userPhoneNumber);
        HttpService.getInstance()
                .createNewDriver(driver)
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
//                            System.out.println(responseBody.string());
                            Driver savedDriver = GsonUtil.getGson().fromJson(responseBody.string
                                    (), Driver.class);
                            savedDriver = savedDriver.getDriver();
                            savedDriver.setPhone(userPhoneNumber);
                            DriverUtil.saveDriver(DriverApp.getContext(), savedDriver);
                            getViewState().startInputPinCod();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, Throwable::printStackTrace);
    }
}
