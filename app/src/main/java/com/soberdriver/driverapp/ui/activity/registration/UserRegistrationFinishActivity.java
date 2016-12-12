package com.soberdriver.driverapp.ui.activity.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.registration
        .UserRegistrationFinishPresenter;
import com.soberdriver.driverapp.presentation.view.registration.UserRegistrationFinishView;
import com.soberdriver.driverapp.ui.activity.AppBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserRegistrationFinishActivity extends AppBaseActivity implements
        UserRegistrationFinishView {
    public static final String TAG = "UserRegistrationFinishActivity";
    @InjectPresenter
    UserRegistrationFinishPresenter mUserRegistrationFinishPresenter;
    @BindView(R.id.registration_finish_main_info_text_view)
    AppCompatTextView mRegistrationFinishMainInfoTextView;
    @BindView(R.id.registration_finish_second_info_text_view)
    AppCompatTextView mRegistrationFinishSecondInfoTextView;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, UserRegistrationFinishActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration_finish);
        ButterKnife.bind(this);
        mRegistrationFinishMainInfoTextView.setText("Спасибо! Вы успешно зарегистрировались в "
                + "системе \"Твой водитель\".");

        mRegistrationFinishSecondInfoTextView.setText(
                "В скором времени с вами свяжется наш менеджер, чтобы договориться о дате и "
                        + "времени встречи в нашем офисе.");
    }
}
