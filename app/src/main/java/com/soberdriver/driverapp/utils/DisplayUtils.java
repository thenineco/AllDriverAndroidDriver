package com.soberdriver.driverapp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;

import com.soberdriver.driverapp.DriverApp;

/**
 * Created by zest .
 */

public abstract class DisplayUtils {
    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into
     *           pixels
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static int convertDpToPixel(float dp) {
        Resources resources = DriverApp.getContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int) px;
    }

    public static int convertDpToPixel(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int) px;
    }

    public static int getDisplayHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getDisplayWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px) {
        Resources resources = DriverApp.getContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    public static int getViewXPosition(View view) {
        int[] positionArray = new int[2];
        view.getLocationInWindow(positionArray);
        return positionArray[0];
    }

    public static int getViewYPosition(View view) {
        int[] positionArray = new int[2];
        view.getLocationInWindow(positionArray);
        return positionArray[1];
    }

}
