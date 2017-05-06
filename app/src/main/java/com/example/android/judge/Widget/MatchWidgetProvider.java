package com.example.android.judge.Widget;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.android.judge.MainActivity;
import com.example.android.judge.R;

import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class MatchWidgetProvider extends AppWidgetProvider {

    private static final String ACTION_CLICK = "ACTION_CLICK";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        // Get all ids
        ComponentName thisWidget = new ComponentName(context,
                MatchWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds) {
            // create some random data

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.match_widget);
            // Set the text
            remoteViews.setTextViewText(R.id.player1_life_widget, String.valueOf(20));
            remoteViews.setTextViewText(R.id.player2_life_widget, String.valueOf(20));

            // Register an onClickListener
            Intent intent = new Intent(context, MatchWidgetProvider.class);
            Intent MatchIntent = new Intent(context, MainActivity.class);


            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getActivity(context,
                    0, MatchIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.player1_life_widget, pendingIntent);
            remoteViews.setOnClickPendingIntent(R.id.player2_life_widget, pendingIntent);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
}