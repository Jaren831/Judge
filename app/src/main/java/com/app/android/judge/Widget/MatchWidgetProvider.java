package com.app.android.judge.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.app.android.judge.MainActivity;
import com.app.android.judge.R;

/**
 * Created by jaren on 5/7/2017.
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

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, MatchIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.player1_life_widget, pendingIntent);
            remoteViews.setOnClickPendingIntent(R.id.player2_life_widget, pendingIntent);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
}
