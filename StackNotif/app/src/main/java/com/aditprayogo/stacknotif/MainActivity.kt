package com.aditprayogo.stacknotif

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var idNotification = 0

    private val stackNotif = ArrayList<NotificationItem>()

    companion object {
        private const val CHANNEL_NAME = "dicoding_channel"
        private const val GROUP_KEY_EMAILS = "group_key_emails"
        private const val NOTIFICATION_REQUEST_CODE = 200
        private const val MAX_NOTIFICATION = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSend.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnSend -> {
                val sender = edtSender.text.toString()
                val message = edtMessage.text.toString()

                if (sender.isEmpty() || message.isEmpty()) {
                    Toast.makeText(this, "Data harus di isi", Toast.LENGTH_SHORT).show()
                } else {
                    stackNotif.add(NotificationItem(idNotification, sender,message))
                    sendNotif()
                    idNotification++
                    edtSender.setText("")
                    edtMessage.setText("")

                    /**
                     * Close keyboard ketika tombol di klik
                     */
                    val methodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    methodManager.hideSoftInputFromWindow(edtMessage.windowToken, 0)
                }
            }
        }
    }

    private fun sendNotif() {
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_notifications_24px)

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent = PendingIntent
            .getActivity(this, NOTIFICATION_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val mBuilder: NotificationCompat.Builder

        /**
         * Melakukan pengecekan jika idNotifikasi lebih kecil dari max notif
         */
        val CHANNEL_ID = "channel_01"
        if (idNotification < MAX_NOTIFICATION) {
            mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("New Email From " + stackNotif[idNotification].sender)
                .setContentText(stackNotif[idNotification].message)
                .setSmallIcon(R.drawable.ic_mail_24px)
                .setLargeIcon(largeIcon)
                .setGroup(GROUP_KEY_EMAILS)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        } else {
            val inboxStyle = NotificationCompat.InboxStyle()
                .addLine("New Email From " + stackNotif[idNotification].sender)
                .addLine("New Email From " + stackNotif[idNotification - 1 ].sender)
                .setBigContentTitle("$idNotification new Emails")
                .setSummaryText("adit@gmail.com")

            mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("$idNotification new Emails")
                .setContentText("adit@gmail.com")
                .setSmallIcon(R.drawable.ic_mail_24px)
                .setGroup(GROUP_KEY_EMAILS)
                .setGroupSummary(true)
                .setContentIntent(pendingIntent)
                .setStyle(inboxStyle)
                .setAutoCancel(true)
        }

        /**
         * Untuk android oreo
         * Perlu menambahkan notification channel
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            /*
            Create or update
             */
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            mBuilder.setChannelId(CHANNEL_ID)

            mNotificationManager.createNotificationChannel(channel)
        }

        val notification = mBuilder.build()

        //kode untuk menampilkan notifikasi
        mNotificationManager.notify(idNotification, notification)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        stackNotif.clear()
        idNotification = 0
    }
}
