package com.soberdriver.driverapp.ui.fragment;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.BeginningOfWorkPresenter;
import com.soberdriver.driverapp.presentation.view.BeginningOfWorkView;
import com.soberdriver.driverapp.ui.activity.MainDriverActivity;
import com.soberdriver.driverapp.ui.activity.UserProfileActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BeginningOfWorkFragment extends AppBaseFragment implements BeginningOfWorkView {
    public static final String TAG = "BeginningOfWorkFragment";
    @InjectPresenter
    BeginningOfWorkPresenter mBeginningOfWorkPresenter;
    @BindView(R.id.layout_driver_open_profile_btn)
    FrameLayout mLayoutDriverOpenProfileBtn;
    @BindView(R.id.beginning_of_work_get_started_btn)
    AppCompatButton mBeginningOfWorkGetStartedBtn;
    @BindView(R.id.beginning_of_invite_btn)
    AppCompatTextView mBeginningOfInviteBtn;
    @BindView(R.id.beginning_of_work_skin_info_text_view)
    AppCompatTextView mBeginningOfWorkSkinInfoTextView;
    @BindView(R.id.beginning_of_work_emulation_btn)
    AppCompatButton mEmulationBtn;
    private boolean started;
    public static boolean inOfficial;
    private GetStartedDialogFragment getStartedDialogFragment;
    private GetFinishedDialogFragment getFinishedDialogFragment;

    public static BeginningOfWorkFragment newInstance() {
        BeginningOfWorkFragment fragment = new BeginningOfWorkFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beginning_of_work, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDialogFragmentParams();

    }

    private void setDialogFragmentParams() {
        getStartedDialogFragment =
                new GetStartedDialogFragment();

        getFinishedDialogFragment =
                new GetFinishedDialogFragment();

        getStartedDialogFragment.setGetStartedDialogListener(
                new GetStartedDialogFragment.GetStartedDialogListener() {
                    @Override
                    public void inOfficial() {
                        getStartedDialogFragment.dismiss();
                        setOfficialMode();
                    }

                    @Override
                    public void noOfficial() {
                        getStartedDialogFragment.dismiss();
                        setNoOfficialMode();
                    }
                });

        getFinishedDialogFragment.setOnClickListener(view -> {
            switch (view.getId()) {
                case R.id.dialog_get_finished_yes_btn:
                    setStartedMode();
                    getFinishedDialogFragment.dismiss();
                    break;
                case R.id.dialog_get_finished_no_btn:
                    getFinishedDialogFragment.dismiss();
                    break;
            }
        });
    }

    private void setOfficialMode() {
        mBeginningOfWorkSkinInfoTextView.setText(
                getResources().getString(R.string.in_official_skin));
    }

    private void setNoOfficialMode() {
        mBeginningOfWorkSkinInfoTextView.setText(
                getResources().getString(R.string.no_official_skin));
    }

    @OnClick({R.id.layout_driver_open_profile_btn, R.id.beginning_of_work_get_started_btn,
            R.id.beginning_of_invite_btn, R.id.beginning_of_work_emulation_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_driver_open_profile_btn:
                openUserProfile();
                break;
            case R.id.beginning_of_work_get_started_btn:
                startOrFinishWorkTime();
                break;
            case R.id.beginning_of_invite_btn:
                break;
            case R.id.beginning_of_work_emulation_btn:
                ((MainDriverActivity) getActivity()).startActiveOrderFragment();
                break;
        }
    }

    private void startOrFinishWorkTime() {
        if (started) {
            getFinishedDialogFragment.show(getActivity().getSupportFragmentManager(),
                    "Get finished dialog");
        } else {
            setFinishedMode();
            getStartedDialogFragment.show(getActivity().getSupportFragmentManager(),
                    "Get started dialog");
        }
    }

    private void openUserProfile() {
        startActivity(UserProfileActivity.getIntent(getContext()));
    }

    public void setStartedMode() {
        started = false;
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = getContext().getTheme();
        theme.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        int color = typedValue.data;
        mEmulationBtn.setVisibility(View.GONE);
        mBeginningOfWorkGetStartedBtn.setBackgroundColor(color);
        mBeginningOfWorkGetStartedBtn.setTextColor(Color.WHITE);
        mBeginningOfWorkGetStartedBtn.setText("Открыть смену");
    }

    public void setFinishedMode() {
        started = true;
        mEmulationBtn.setVisibility(View.VISIBLE);
        mBeginningOfWorkGetStartedBtn.setTextColor(Color.BLACK);
        mBeginningOfWorkGetStartedBtn.setBackgroundColor(Color.WHITE);
        mBeginningOfWorkGetStartedBtn.setText("Закрыть смену");
    }
}
