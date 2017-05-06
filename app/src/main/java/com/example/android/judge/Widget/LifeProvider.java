package com.example.android.judge.Widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by jaren on 5/5/2017.
 */

public class LifeProvider implements RemoteViewsService.RemoteViewsFactory {

    private Context context = null;

    public ListProvider(Context context, Intent intent) {
        this.context = context;
        int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        
    }

}
