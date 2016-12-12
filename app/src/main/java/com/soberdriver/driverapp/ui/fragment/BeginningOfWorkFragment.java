package com.soberdriver.driverapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.presentation.presenter.BeginningOfWorkPresenter;
import com.soberdriver.driverapp.presentation.view.BeginningOfWorkView;

public class BeginningOfWorkFragment extends AppBaseFragment implements BeginningOfWorkView {
    public static final String TAG = "BeginningOfWorkFragment";
    @InjectPresenter
    BeginningOfWorkPresenter mBeginningOfWorkPresenter;

    public static BeginningOfWorkFragment newInstance() {
        BeginningOfWorkFragment fragment = new BeginningOfWorkFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_beginning_of_work, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
