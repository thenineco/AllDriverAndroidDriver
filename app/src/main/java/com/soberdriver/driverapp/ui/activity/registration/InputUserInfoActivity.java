package com.soberdriver.driverapp.ui.activity.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.module.network.networkmodule.models.driver.Driver;
import com.module.network.networkmodule.utils.DriverUtil;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.registration.InputUserInfoPresenter;
import com.soberdriver.driverapp.presentation.view.registration.InputUserInfoView;
import com.soberdriver.driverapp.ui.activity.AppBaseActivity;
import com.soberdriver.driverapp.ui.view.AppCustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputUserInfoActivity extends AppBaseActivity implements InputUserInfoView {
    public static final String TAG = "InputUserInfoActivity";
    private static final int PICK_IMAGE_REQUEST = 1;
    @InjectPresenter
    InputUserInfoPresenter mInputUserInfoPresenter;
    @BindView(R.id.user_info_name_edit_text)
    AppCompatEditText mUserInfoNameEditText;
    @BindView(R.id.user_info_last_name_edit_text)
    AppCompatEditText mUserInfoLastNameEditText;
    @BindView(R.id.user_info_otchestvo_edit_text)
    AppCompatEditText mUserInfoOtchestvoEditText;
    @BindView(R.id.user_info_resident_check_box)
    AppCompatCheckBox mUserInfoResidentCheckBox;
    @BindView(R.id.user_info_birth_day_edit_text)
    AppCompatEditText mUserInfoBirthDayEditText;
    @BindView(R.id.user_info_gender_male_radio_bitton)
    AppCompatRadioButton mUserInfoGenderMaleRadioBitton;
    @BindView(R.id.user_info_gender_female_radio_bitton)
    AppCompatRadioButton mUserInfoGenderFemaleRadioBitton;
    @BindView(R.id.user_info_email_edit_text)
    AppCompatEditText mUserInfoEmailEditText;
    @BindView(R.id.user_info_passport_serial_edit_text)
    AppCompatEditText mUserInfoPassportSerialEditText;
    @BindView(R.id.user_info_passport_main_photo_load_btn)
    AppCompatButton mUserInfoPassportMainPhotoLoadBtn;
    @BindView(R.id.user_info_passport_second_photo_load_btn)
    AppCompatButton mUserInfoPassportSecondPhotoLoadBtn;
    @BindView(R.id.user_info_selfie_with_passport_load_btn)
    AppCompatButton mUserInfoSelfieWithPassportLoadBtn;
    @BindView(R.id.user_info_driver_id_number_edit_text)
    AppCompatEditText mUserInfoDriverIdNumberEditText;
    @BindView(R.id.user_info_driver_id_date_edit_text)
    AppCompatEditText mUserInfoDriverIdDateEditText;
    @BindView(R.id.user_info_driver_id_main_photo_load_btn)
    AppCompatButton mUserInfoDriverIdMainPhotoLoadBtn;
    @BindView(R.id.user_info_driver_id_second_photo_load_btn)
    AppCompatButton mUserInfoDriverIdSecondPhotoLoadBtn;
    @BindView(R.id.user_info_terms_of_use_check_box)
    AppCompatCheckBox mUserInfoTermsOfUseCheckBox;
    @BindView(R.id.user_info_personal_data_check_box)
    AppCompatCheckBox mUserInfoPersonalDataCheckBox;
    @BindView(R.id.user_info_register_btn)
    AppCompatButton mUserInfoRegisterBtn;
    @BindView(R.id.user_info_toolbar)
    AppCustomToolbar mUserInfoToolbar;
    private Driver mDriver;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, InputUserInfoActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_user_info);
        ButterKnife.bind(this);
        mDriver = DriverUtil.getDriver(this);
        if (mDriver == null) {
            mDriver = new Driver();
        }
        setToolbar();
    }

    private void setToolbar() {
        mUserInfoToolbar.setToolbarTitle("Регистрация");
        Toolbar toolbar = mUserInfoToolbar.getToolbar();
        setSupportActionBar(toolbar);
    }

    @OnClick({R.id.user_info_passport_main_photo_load_btn,
            R.id.user_info_passport_second_photo_load_btn,
            R.id.user_info_selfie_with_passport_load_btn,
            R.id.user_info_driver_id_main_photo_load_btn,
            R.id.user_info_driver_id_second_photo_load_btn, R.id.user_info_register_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_info_passport_main_photo_load_btn:
                openGalleryForTakeNewPhoto();
                break;
            case R.id.user_info_passport_second_photo_load_btn:
                openGalleryForTakeNewPhoto();
                break;
            case R.id.user_info_selfie_with_passport_load_btn:
                openGalleryForTakeNewPhoto();
                break;
            case R.id.user_info_driver_id_main_photo_load_btn:
                openGalleryForTakeNewPhoto();
                break;
            case R.id.user_info_driver_id_second_photo_load_btn:
                openGalleryForTakeNewPhoto();
                break;
            case R.id.user_info_register_btn:
                mInputUserInfoPresenter.saveUserInfo(mDriver);
                break;
        }
    }

    @Override
    public void finishUserRegistration() {
        startActivity(UserRegistrationFinishActivity.getIntent(this));
    }

    public void openGalleryForTakeNewPhoto() {
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
}
