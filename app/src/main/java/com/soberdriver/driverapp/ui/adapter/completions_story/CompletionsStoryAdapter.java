package com.soberdriver.driverapp.ui.adapter.completions_story;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soberdriver.driverapp.R;
import com.soberdriver.driverapp.model.Completion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zest
 */

public class CompletionsStoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Completion> mCompletionList = new ArrayList<>();

    public CompletionsStoryAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_completion, parent, false);
        return new CompletionsStoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Completion completion = mCompletionList.get(position);
        ((CompletionsStoryViewHolder) holder).bindViewHolder(completion);
    }

    @Override
    public int getItemCount() {
        return mCompletionList.size();
    }

    public void setCompletionList(List<Completion> completionList) {
        mCompletionList = completionList;
        notifyDataSetChanged();
    }
}
