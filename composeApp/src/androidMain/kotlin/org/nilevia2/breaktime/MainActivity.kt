package org.nilevia2.breaktime

import NotificationSchedulerImpl
import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.pm.PackageManager
import android.os.Build
import notification.NotificationScheduler
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import data.ConfigRepository
import notification.NotificationManager
import org.nilevia2.breaktime.data.getConfigDatabase
import ui.BreakTimeApp
import ui.viewmodel.ConfigViewModel
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    private val NOTIFICATION_PERMISSION_REQUEST_CODE = 1001

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the NotificationManager with the Android-specific implementation
        NotificationManager.initialize(NotificationSchedulerImpl(this))
        requestNotificationPermission()

        setContent {
            val dao = getConfigDatabase(applicationContext).configDao()
            val repository = remember {
                ConfigRepository(dao)
            }
            val viewModel = remember {
                ConfigViewModel(repository)
            }

            BreakTimeApp(viewModel)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(POST_NOTIFICATIONS), NOTIFICATION_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == NOTIFICATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText( applicationContext,"permission notif granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText( applicationContext,"decline", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
