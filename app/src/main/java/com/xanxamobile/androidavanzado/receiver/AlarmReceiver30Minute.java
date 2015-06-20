package com.xanxamobile.androidavanzado.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.xanxamobile.androidavanzado.AAListActivityMenu;
import com.xanxamobile.androidavanzado.R;
import com.xanxamobile.androidavanzado.utils.Randomize;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by alemarcha26 on 12/6/15.
 */
public class AlarmReceiver30Minute extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Notificación", Toast.LENGTH_SHORT).show();
        NotificationManager mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
      NotificationManager  notificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent mIntent = new Intent(context, AAListActivityMenu.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("La hora actual es");
        Calendar c=Calendar.getInstance();
        Date d=c.getTime();
        SimpleDateFormat s= new SimpleDateFormat("HH:mm:ss");

        builder.setContentText(s.format(d));
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentIntent(pendingIntent);

        notificationManager.notify(Randomize.random(10000), builder.build());


    }
}
