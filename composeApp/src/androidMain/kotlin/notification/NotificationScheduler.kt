import android.content.Context
import androidx.work.*
import notification.NotificationScheduler
import notification.NotificationWorker
import java.util.concurrent.TimeUnit

class NotificationSchedulerImpl(private val context: Context) : NotificationScheduler {

    companion object {
        private const val WORK_TAG = "NotificationWork"
    }

    override fun scheduleNotifications(startTime: Long, endTime: Long, intervalMinutes: Long) {
        val workManager = WorkManager.getInstance(context)
        val currentTime = System.currentTimeMillis()

        if (currentTime in startTime..endTime) {
            val interval = PeriodicWorkRequestBuilder<NotificationWorker>(intervalMinutes, TimeUnit.MINUTES)
                .setInitialDelay(0, TimeUnit.MINUTES)
                .addTag(WORK_TAG)
                .build()

            workManager.enqueueUniquePeriodicWork(
                WORK_TAG,
                ExistingPeriodicWorkPolicy.UPDATE,
                interval
            )
        }
    }

    override fun stopNotifications() {
        val workManager = WorkManager.getInstance(context)
        workManager.cancelAllWorkByTag(WORK_TAG)
    }

    override fun updateSchedule(startTime: Long, endTime: Long, intervalMinutes: Long) {
        stopNotifications()
        scheduleNotifications(startTime, endTime, intervalMinutes)
    }
}
