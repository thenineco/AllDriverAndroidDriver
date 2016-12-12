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
    private boolean started;
    public static boolean inOfficial;
    private GetStartedDialogFragment getStartedDialogFragment;

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
        setDialogParams();

    }

    private void setDialogParams() {
        getStartedDialogFragment =
                new GetStartedDialogFragment();
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
    }

    private void setOfficialMode() {
        mBeginningOfWorkSkinInfoTextView.setText(getResources().getString(R.string.in_official_skin));
    }

    private void setNoOfficialMode() {
        mBeginningOfWorkSkinInfoTextView.setText(getResources().getString(R.string.no_official_skin));
    }

    @OnClick({R.id.layout_driver_open_profile_btn, R.id.beginning_of_work_get_started_btn,
            R.id.beginning_of_invite_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_driver_open_profile_btn:
                break;
            case R.id.beginning_of_work_get_started_btn:
                if (started) {
                    setStartedMode();
                    started = false;
                } else {
                    setFinishedMode();
                    started = true;
                    getStartedDialogFragment.show(getActivity().getSupportFragmentManager(),
                            "Get started dialog");
                }
                break;
            case R.id.beginning_of_invite_btn:
                break;
        }
    }

    public void setStartedMode() {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = getContext().getTheme();
        theme.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        int color = typedValue.data;
        mBeginningOfWorkGetStartedBtn.setBackgroundColor(color);
        mBeginningOfWorkGetStartedBtn.setTextColor(Color.WHITE);
        mBeginningOfWorkGetStartedBtn.setText("Открыть смену");
    }

    public void setFinishedMode() {
        mBeginningOfWorkGetStartedBtn.setTextColor(Color.BLACK);
        mBeginningOfWorkGetStartedBtn.setBackgroundColor(Color.WHITE);
        mBeginningOfWorkGetStartedBtn.setText("Закрыть смену");
    }
}
