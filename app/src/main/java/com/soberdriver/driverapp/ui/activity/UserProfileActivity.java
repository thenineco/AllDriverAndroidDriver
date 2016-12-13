package com.soberdriver.driverapp.ui.activity;

import static android.R.attr.bitmap;
import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.data;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.UserProfilePresenter;
import com.soberdriver.driverapp.presentation.view.UserProfileView;
import com.soberdriver.driverapp.ui.fragment.GetPhotoDialogFragment;
import com.soberdriver.driverapp.ui.view.RatingBarView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppBaseActivity implements UserProfileView {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final String TAG = "UserProfileActivity";
    private static final int PICK_IMAGE_REQUEST = 2;

    @InjectPresenter
    UserProfilePresenter mUserProfilePresenter;

    @BindView(R.id.user_profile_user_rating_view)
    RatingBarView mUserRatingView;
    @BindView(R.id.user_profile_driver_name_text_view)
    AppCompatTextView mDriverNameTextView;
    @BindView(R.id.user_profile_driver_age_info_text_view)
    AppCompatTextView mDriverAgeInfoTextView;
    @BindView(R.id.user_profile_driver_experience_info_text_view)
    AppCompatTextView mDriverExperienceInfoTextView;
    @BindView(R.id.user_profile_driver_details_btn)
    AppCompatTextView mDriverDetailsBtn;
    @BindView(R.id.user_profile_avatar_image_view)
    CircleImageView mAvatarImageView;
    @BindView(R.id.user_profile_money_text_view)
    AppCompatTextView mMoneyTextView;
    @BindView(R.id.user_profile_money_fill_up_btn)
    AppCompatButton mMoneyFillUpBtn;
    @BindView(R.id.user_profile_completions_story_btn)
    AppCompatTextView mCompletionsStoryBtn;
    @BindView(R.id.user_profile_driver_rating_text_view)
    AppCompatTextView mDriverRatingTextView;
    @BindView(R.id.user_profile_driver_rating_position_text_view)
    AppCompatTextView mDriverRatingPositionTextView;
    @BindView(R.id.user_profile_how_to_increase_the_rating_btn)
    AppCompatTextView mHowToIncreaseTheRatingBtn;
    @BindView(R.id.user_profile_last_trip_date_text_view)
    AppCompatTextView mLastTripDateTextView;
    @BindView(R.id.user_profile_last_trip_start_text_view)
    AppCompatTextView mLastTripStartTextView;
    @BindView(R.id.user_profile_last_trip_finish_text_view)
    AppCompatTextView mLastTripFinishTextView;
    @BindView(R.id.user_profile_last_trip_auto_logo_image_view)
    AppCompatTextView mLastTripAutoLogoImageView;
    @BindView(R.id.user_profile_driver_contract_btn)
    CardView mDriverContractBtn;
    @BindView(R.id.user_profile_change_profile_btn)
    CardView mChangeProfileBtn;
    @BindView(R.id.user_profile_back_btn)
    AppCompatImageView mBackBtn;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, UserProfileActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.user_profile_driver_details_btn, R.id.user_profile_money_fill_up_btn,
            R.id.user_profile_completions_story_btn,
            R.id.user_profile_how_to_increase_the_rating_btn, R.id.user_profile_driver_contract_btn,
            R.id.user_profile_change_profile_btn, R.id.user_profile_back_btn,
            R.id.user_profile_avatar_image_view, R.id.user_profile_rating_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_profile_driver_details_btn:
                break;
            case R.id.user_profile_money_fill_up_btn:
                openMoneyFillUpActivity();
                break;
            case R.id.user_profile_completions_story_btn:
                break;
            case R.id.user_profile_how_to_increase_the_rating_btn:
                break;
            case R.id.user_profile_driver_contract_btn:
                break;
            case R.id.user_profile_change_profile_btn:
                break;
            case R.id.user_profile_back_btn:
                finish();
                break;
            case R.id.user_profile_avatar_image_view:
                showPhotoChangeDialog();
                break;
            case R.id.user_profile_rating_btn:
                openRatingActivity();
                break;
        }
    }

    private void openRatingActivity() {
        startActivity(RatingActivity.getIntent(this));
    }

    private void openMoneyFillUpActivity() {
        startActivity(VirtualAccountActivity.getIntent(this, true));
    }

    private void showPhotoChangeDialog() {
        GetPhotoDialogFragment dialogFragment = new GetPhotoDialogFragment();
        dialogFragment.setClickListener(view -> {
            switch (view.getId()) {
                case R.id.dialog_create_photo:
                    openCameraForCreateNewPhoto();
                    dialogFragment.dismiss();
                    break;
                case R.id.dialog_get_from_library:
                    openGalleryForTakeNewPhoto();
                    dialogFragment.dismiss();
                    break;
                case R.id.dialog_cancel:
                    dialogFragment.dismiss();
                    break;
            }
        });
        dialogFragment.show(getSupportFragmentManager(), "Change photo dialog");
    }

    public void openCameraForCreateNewPhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void openGalleryForTakeNewPhoto() {
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if (imageBitmap != null) {
                mAvatarImageView.setImageBitmap(imageBitmap);
                imageBitmap.recycle();
            }
        } else if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null
                && data.getData() != null) {
            Uri uri = data.getData();
            Glide.with(this)
                    .load(uri)
                    .thumbnail(.5f)
                    .override(300, 300)
                    .into(mAvatarImageView);
        }
    }
}
