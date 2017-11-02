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

    /**
     * Get time ago that milliseconds date occurred
     *
     * @param millis
     * @return time string
     */
    public synchronized static String timeAgo(final long millis, Context context) {
        return time(System.currentTimeMillis() - millis, context);
    }


    public synchronized static String time(Long distanceMillis, Context context) {

        final double seconds = distanceMillis / 1000;
        final double minutes = seconds / 60;
        final double hours = minutes / 60;
        final double days = hours / 24;
        final double years = days / 365;

        final String time;
        if (seconds < 45) {
            time = context.getString(R.string.time_seconds);
        } else if (seconds < 90 || minutes < 45) {
            time = context.getResources().getQuantityString(R.plurals.time_minute, minutes < 2 ? 1 : 2, Math.round
                    (minutes));
        } else if (minutes < 90 || hours < 24) {
            time = context.getResources().getQuantityString(R.plurals.time_hour, hours < 2 ? 1 : 2, Math.round(hours));
        } else if (hours < 48 || days < 30) {
            time = context.getResources().getQuantityString(R.plurals.time_day, days < 2 ? 1 : 2, Math.round(days));
        } else if (days < 60 || days < 365) {
            time = context.getResources().getQuantityString(R.plurals.time_month, (days / 30) < 2 ? 1 : 2, Math.round(days / 30));
        } else {
            time = context.getResources().getQuantityString(R.plurals.time_year, years < 2 ? 1 : 2, Math.round(years));
        }

        return time + " " + context.getString(R.string.time_ago);

    }
}
