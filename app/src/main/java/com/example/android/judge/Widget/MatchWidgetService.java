package com.example.android.judge.Widget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by jaren on 5/5/2017.
 */

public class MatchWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (new LifeProvider(this, intent));
    }
}

