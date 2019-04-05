
class StartMyServiceAtBootReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
            val serviceIntent = Intent(context, MyService::class.java)
            context?.startService(serviceIntent)
        }
    }

}

ဒါကတော့ ဖုန်းစဖွင့်တာနဲ့ကို့ App ကို run အောင်

BroadcastReciever တစ်ခုဆောက်ပါ။

 <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />

 Menifest ထဲမှာ ပါမစ်ရှင်ထည့်ပေးရမယ်။

 Reciever လည်းကြေညာပေးရမယ်။


<receiver
                android:name=".StartMyServiceAtBootReceiver"
                android:label="StartMyServiceAtBootReceiver">
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED" />
            </intent-filter>
        </receiver>