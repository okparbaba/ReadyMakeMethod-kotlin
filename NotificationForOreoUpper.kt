class MyService : Service() {
    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // do your jobs here
        startForegroundTwo()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startForegroundTwo() {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel("my_service", "My Background Service")
            } else {
                // If earlier version channel ID is not used
                // https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#NotificationCompat.Builder(android.content.Context)
                ""
            }
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            notificationIntent, 0
        )

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
        val notification = notificationBuilder.setOngoing(true)
            .setSmallIcon(R.drawable.car_icon)
            .setContentTitle(getString(R.string.app_name))
            .setContentText("Waiting to Arrive")
            .setContentIntent(pendingIntent)
            .build()
        startForeground(101, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(
            channelId,
            channelName, NotificationManager.IMPORTANCE_NONE
        )
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }
}


ပီးရင် 
Application Level က

OnCreate မှာ

 @TargetApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(Intent(this, MyService::class.java))
        }else{
            startService(Intent(this, MyService::class.java))
        }
    }


အဲ့ဒါသွားထည့်ရမယ်။

<service android:name=".MyService" android:label="My Service">
        </service>

Menifest မှာလည်းကြေညာပေးရမယ်။