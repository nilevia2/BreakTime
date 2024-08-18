package notification

object NotificationManager {

    private lateinit var notificationScheduler: NotificationScheduler

    fun initialize(scheduler: NotificationScheduler) {
        notificationScheduler = scheduler
    }

    fun startScheduling(startTime: Long, endTime: Long, intervalMinutes: Long) {
        notificationScheduler.scheduleNotifications(startTime, endTime, intervalMinutes)
    }

    fun stopScheduling() {
        notificationScheduler.stopNotifications()
    }

    fun updateScheduling(startTime: Long, endTime: Long, intervalMinutes: Long) {
        notificationScheduler.updateSchedule(startTime, endTime, intervalMinutes)
    }
}
