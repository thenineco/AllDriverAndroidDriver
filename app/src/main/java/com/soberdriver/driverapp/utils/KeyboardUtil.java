package com.soberdriver.driverapp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by zest .
 */

public abstract class KeyboardUtil {

    private static InputMethodManager imm;

    public static void openKeyboard(Context context) {
        imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void closeKeyboard(Context context, View view) {
        imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void closeKeyboard(Context context) {
        ((InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE))
                .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }
}
