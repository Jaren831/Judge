package com.example.android.judge.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.example.android.judge.MainActivity;
import com.example.android.judge.R;

import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class MatchWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews remoteViews = updateWidgetView(context,
                    appWidgetId);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        context.startService(new Intent(context, MatchWidgetService.class));
    }

    private RemoteViews updateWidgetView(Context context, int appWidgetId) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.match_widget);
        Intent svcIntent = new Intent(context, MatchWidgetService.class);
        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
        remoteViews.setTextViewText(R.id.player1_life_widget, "");
        remoteViews.setTextViewText(R.id.player2_life_widget, "");
        return remoteViews;
    }

}

