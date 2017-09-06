package com.soberdriver.driverapp.ui.activity;

import static com.module.network.networkmodule.utils.DriverUtil.getDriver;

import android.os.Bundle;

import com.module.network.networkmodule.models.driver.Driver;
import com.module.network.networkmodule.utils.DriverUtil;
import com.soberdriver.driverapp.ui.activity.registration.InputPhoneNumberActivity;

/**
 * Created by zest
 */

public class LaunchActivity extends AppBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (haveCurrentUser()) {
            startActivity(MainDriverActivity.getIntent(this));
        } else {
            startActivity(InputPhoneNumberActivity.getIntent(this));
        }
    }

    private boolean haveCurrentUser() {
        Driver driver = DriverUtil.getDriver(this);
        return driver != null;
    }
}
