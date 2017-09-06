package com.soberdriver.driverapp.presentation.presenter;


import com.module.network.networkmodule.api_v1.HttpService;
import com.module.network.networkmodule.models.driver.Driver;
import com.module.network.networkmodule.utils.DriverUtil;
import com.soberdriver.driverapp.DriverApp;
import com.soberdriver.driverapp.presentation.view.MainDriverView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

@InjectViewState
public class MainDriverPresenter extends MvpPresenter<MainDriverView> {


}
