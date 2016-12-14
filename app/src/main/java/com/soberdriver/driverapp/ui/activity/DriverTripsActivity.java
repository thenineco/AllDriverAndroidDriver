package com.soberdriver.driverapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.model.DriverTrip;
import com.soberdriver.driverapp.presentation.presenter.DriverTripsPresenter;
import com.soberdriver.driverapp.presentation.view.DriverTripsView;
import com.soberdriver.driverapp.ui.adapter.driver_trips.DriverTripsAdapter;
import com.soberdriver.driverapp.ui.view.AppCustomToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverTripsActivity extends AppBaseActivity implements DriverTripsView {
    public static final String TAG = "DriverTripsActivity";
    @InjectPresenter
    DriverTripsPresenter mDriverTripsPresenter;
    @BindView(R.id.driver_trips_toolbar)
    AppCustomToolbar mToolbar;
    @BindView(R.id.driver_trips_back_btn)
    AppCompatImageView mBackBtn;
    @BindView(R.id.driver_trips_recycler_view)
    RecyclerView mDriverTripsRecyclerView;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, DriverTripsActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_trips);
        ButterKnife.bind(this);
        setToolbar();
        setRecyclerView();
        setDriverTrips();
    }

    private void setDriverTrips() {
        List<DriverTrip> driverTrips = new ArrayList<>();
        for (int x = 0; x < 15; x++) {
            driverTrips.add(new DriverTrip());
        }
        ((DriverTripsAdapter) mDriverTripsRecyclerView.getAdapter()).setDriverTripList(driverTrips);
    }

    private void setRecyclerView() {
        DriverTripsAdapter driverTripsAdapter = new DriverTripsAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mDriverTripsRecyclerView.setAdapter(driverTripsAdapter);
        mDriverTripsRecyclerView.setLayoutManager(layoutManager);
    }

    private void setToolbar() {
        mToolbar.setToolbarTitle("Мои поездки");
        Toolbar toolbar = mToolbar.getToolbar();
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.driver_trips_back_btn)
    public void onClick() {
        finish();
    }
}
