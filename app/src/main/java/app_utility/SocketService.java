package app_utility;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.antimatter.tasteful.MainActivity;
import com.antimatter.tasteful.R;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static androidx.core.app.NotificationCompat.PRIORITY_MAX;

public class SocketService extends Service {
    public static SocketService refOfService;

    NotificationManager notifyMgr;
    NotificationCompat.Builder nBuilder;
    NotificationCompat.InboxStyle inboxStyle;

    String channelId = "app_utility.ReminderService";
    String channelName = "tracking";

    public SocketService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        refOfService = this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground();
           /* MyTask myTask = new MyTask();
            myTask.execute();*/
            //sendRequest();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startForeground() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), createNotificationChannel());
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(PRIORITY_MAX)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(101, notification);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel() {
        NotificationChannel chan = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        notifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        assert notifyMgr != null;
        notifyMgr.createNotificationChannel(chan);
        return channelId;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    void startSocket() {
        final int port = 12345;
        ServerSocket listener;
        try {
            listener = new ServerSocket(port);
            while (true) {
                Socket socket = listener.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream out = new PrintStream(socket.getOutputStream());
                for (String inputLine; (inputLine = in.readLine()) != null; ) {
                    StringBuilder outputStringBuilder = new StringBuilder();
                    char inputLineChars[] = inputLine.toCharArray();
                    for (char c : inputLineChars)
                        outputStringBuilder.append(Character.toChars(c + 1));
                    out.println(outputStringBuilder);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
