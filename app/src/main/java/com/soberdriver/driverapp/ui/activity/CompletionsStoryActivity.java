package com.soberdriver.driverapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.model.Completion;
import com.soberdriver.driverapp.model.DriverTrip;
import com.soberdriver.driverapp.presentation.presenter.CompletionsStoryPresenter;
import com.soberdriver.driverapp.presentation.view.CompletionsStoryView;
import com.soberdriver.driverapp.ui.adapter.completions_story.CompletionsStoryAdapter;
import com.soberdriver.driverapp.ui.adapter.driver_trips.DriverTripsAdapter;
import com.soberdriver.driverapp.ui.view.AppCustomToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompletionsStoryActivity extends AppBaseActivity implements CompletionsStoryView {
    public static final String TAG = "CompletionsStoryActivity";
    @InjectPresenter
    CompletionsStoryPresenter mCompletionsStoryPresenter;
    @BindView(R.id.completions_story_toolbar)
    AppCustomToolbar mToolbar;
    @BindView(R.id.completions_story_back_btn)
    AppCompatImageView mBackBtn;
    @BindView(R.id.completions_story_select_period_btn)
    LinearLayout mSelectPeriodBtn;
    @BindView(R.id.completions_show_btn)
    AppCompatButton mCompletionsShowBtn;
    @BindView(R.id.completions_story_period_select_container)
    LinearLayout mStoryPeriodSelectContainer;
    @BindView(R.id.completions_story_recyclerView)
    RecyclerView mCompletionsStoryRecyclerView;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, CompletionsStoryActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completions_story);
        ButterKnife.bind(this);
        setRecyclerView();
        setCompletionsStory();
        setToolbar();
    }

    private void setToolbar() {
        mToolbar.setToolbarTitle("История пополнений");
        Toolbar toolbar = mToolbar.getToolbar();
        setSupportActionBar(toolbar);
    }

    private void setCompletionsStory() {
        List<Completion> completions = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            completions.add(new Completion());
        }
        ((CompletionsStoryAdapter) mCompletionsStoryRecyclerView.getAdapter()).setCompletionList(
                completions);
    }

    private void setRecyclerView() {
        CompletionsStoryAdapter completionsStoryAdapter = new CompletionsStoryAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mCompletionsStoryRecyclerView.setLayoutManager(layoutManager);
        mCompletionsStoryRecyclerView.setAdapter(completionsStoryAdapter);
    }

    @OnClick({R.id.completions_story_back_btn, R.id.completions_story_select_period_btn,
            R.id.completions_show_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.completions_story_back_btn:
                finish();
                break;
            case R.id.completions_story_select_period_btn:
                mStoryPeriodSelectContainer.setVisibility(View.VISIBLE);
                break;
            case R.id.completions_show_btn:
                mStoryPeriodSelectContainer.setVisibility(View.GONE);
                break;
        }
    }
}
