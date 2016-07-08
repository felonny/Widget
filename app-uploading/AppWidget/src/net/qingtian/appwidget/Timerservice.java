package net.qingtian.appwidget;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;



import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class Timerservice extends Service {
	private Timer timer;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				updateViews();
			}
		}, 0, 1000);
	}
	public void updateViews(){
		String time = sdf.format(new Date());
		RemoteViews rv = new RemoteViews(getPackageName(), R.layout.widget_layout);
		rv.setTextViewText(R.id.widgetButton, time);
		AppWidgetManager manager =  AppWidgetManager.getInstance(getApplicationContext());
		
		ComponentName cn = new ComponentName(getApplicationContext(), FirstWidgetProvider.class);
		manager.updateAppWidget(cn, rv);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		timer = null;
	}

}
