package com.soberdriver.driverapp.ui.adapter.driver_trips;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.model.DriverTrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zest
 */

public class DriverTripsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<DriverTrip> mDriverTripList = new ArrayList<>();

    public DriverTripsAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_driver_trips, parent, false);
        return new DriverTripsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DriverTrip driverTrip = mDriverTripList.get(position);
        ((DriverTripsViewHolder) holder).bindViewHolder(driverTrip);
    }

    @Override
    public int getItemCount() {
        return mDriverTripList.size();
    }

    public void setDriverTripList(List<DriverTrip> driverTripList) {
        mDriverTripList = driverTripList;
        notifyDataSetChanged();
    }
}
