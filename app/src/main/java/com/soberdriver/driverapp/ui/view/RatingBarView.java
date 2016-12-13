package com.soberdriver.driverapp.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.soberdriver.driverapp.R;

/**
 * Created by zest
 */

public class RatingBarView extends LinearLayout {

    public interface OnRatingListener {
        void onRating(Object bindObject, int RatingScore);
    }

    private boolean mClickable = true;
    private OnRatingListener onRatingListener;
    private Object bindObject;
    private float starImageSize;
    private int starCount;
    private int starEmptyRes;
    private int starFullRes;
    private int mStarCount;

    public void setClickable(boolean clickable) {
        this.mClickable = clickable;
    }

    public RatingBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RatingBarView);
        starImageSize = ta.getDimension(R.styleable.RatingBarView_starImageSize, 20);
        starCount = ta.getInteger(R.styleable.RatingBarView_starCount, 5);
        starEmptyRes = R.drawable.star_empty;
        starFullRes = R.drawable.star_full;
        ta.recycle();

        for (int i = 0; i < starCount; ++i) {
            AppCompatImageView imageView = getStarImageView(context, attrs);
            imageView.setOnClickListener(v -> {
                if (mClickable) {
                    mStarCount = indexOfChild(v) + 1;
                    setStar(mStarCount, true);
                    if (onRatingListener != null) {
                        onRatingListener.onRating(bindObject, mStarCount);
                    }
                }
            });
            addView(imageView);
        }
    }

    private AppCompatImageView getStarImageView(Context context, AttributeSet attrs) {
        AppCompatImageView imageView = new AppCompatImageView(context);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(Math.round(starImageSize),
                Math.round(starImageSize));
        imageView.setLayoutParams(param);
        // TODO:you can change gap between two stars use the padding
        imageView.setPadding(0, 0, 40, 0);
        imageView.setBackgroundResource(starEmptyRes);
        imageView.setMaxWidth(10);
        imageView.setMaxHeight(10);
        return imageView;
    }

    public void setStar(int starCount, boolean animation) {
        starCount = starCount > this.starCount ? this.starCount : starCount;
        starCount = starCount < 0 ? 0 : starCount;
        for (int i = 0; i < starCount; ++i) {
            ((AppCompatImageView) getChildAt(i)).setImageResource(starFullRes);
            if (animation) {
                ScaleAnimation sa = new ScaleAnimation(0, 0, 1, 1);
                getChildAt(i).startAnimation(sa);
            }
        }
        for (int i = this.starCount - 1; i >= starCount; --i) {
            ((AppCompatImageView) getChildAt(i)).setImageResource(starEmptyRes);
        }
    }

    public int getStarCount() {
        return mStarCount;
    }

    public void setStarCount(int startCount) {
        this.starCount = starCount;
    }

    public void setStarImageSize(float starImageSize) {
        this.starImageSize = starImageSize;
    }

    public void setBindObject(Object bindObject) {
        this.bindObject = bindObject;
    }

    public void setOnRatingListener(OnRatingListener onRatingListener) {
        this.onRatingListener = onRatingListener;
    }
}