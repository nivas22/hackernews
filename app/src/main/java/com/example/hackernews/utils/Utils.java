package com.example.hackernews.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.hackernews.App;
import com.example.hackernews.R;

import java.text.DateFormat;
import java.util.Date;

public class Utils {

    /**
     * Checks if Internet is available
     *
     * @param context Context
     * @return boolean
     */
    public static boolean isInternetAvailable(Context context) {

        if (context == null) {
            return false;
        }

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Shows simple dialog message. Use instead of toast
     *
     * @param resourceId resource id
     * @param context    Context
     */
    public static void showSimpleDialogMessage(int resourceId, Context context) {
        showSimpleDialogMessage(resourceId, null, context);
    }

    /**
     * Shows simple dialog message. Use instead of toast
     *
     * @param resourceId resource id
     * @param context    Context
     */
    public static void showSimpleDialogMessage(int resourceId, final Runnable runnable, Context context) {

        Activity activity = (Activity) context;
        if (activity.isFinishing()) {
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(resourceId)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                });

        if (runnable != null) {
            builder.setCancelable(false);
        }

        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Shows simple dialog message. Use instead of toast
     *
     * @param message String
     * @param context Context
     */
    public static void showSimpleDialogMessage(String message, Context context) {
        showSimpleDialogMessage(message, null, context);
    }

    /**
     * Shows simple dialog message. Use instead of toast
     *
     * @param message String
     * @param context Context
     */
    public static void showSimpleDialogMessage(String message, final Runnable runnable, Context context) {

        Activity activity = (Activity) context;
        if (activity.isFinishing()) {
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static String getTimeString(long time) {
        DateFormat df = android.text.format.DateFormat.getDateFormat(App.get());
        return df.format(new Date(time));
    }

}
