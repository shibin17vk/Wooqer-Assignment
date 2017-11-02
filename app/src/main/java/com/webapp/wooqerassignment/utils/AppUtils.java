package com.webapp.wooqerassignment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.webapp.wooqerassignment.R;

/**
 * @author shibin
 * @version 1.0
 * @date 02/11/17
 */

public class AppUtils {
    /**
     * Get Network status
     */
    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * @return
     */
    public static  boolean isConnectingToInternet(Context context) {
        return isNetworkAvailable(context);
    }

    /**
     * This method creates and returns the snackbar reference w.r.t the given params
     */
    public static Snackbar getSnackbar(Context context, View view, String message, int snackbarDuration) {
        Snackbar mSnackbar = null;

        if (mSnackbar != null && (mSnackbar.isShown() || mSnackbar.isShownOrQueued())) {
            mSnackbar.dismiss();
        }

        try {
            mSnackbar = Snackbar.make(view, message, snackbarDuration);
            View sbView = mSnackbar.getView();
            mSnackbar.getView().setBackgroundColor(ContextCompat.getColor(context, R.color.snackbar_bg));
            mSnackbar.setActionTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(ContextCompat.getColor(context, R.color.snackbar_message));
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

        return mSnackbar;
    }

}
