package notification

interface NotificationScheduler {
    fun scheduleNotifications(startTime: Long, endTime: Long, intervalMinutes: Long)
    fun stopNotifications()
    fun updateSchedule(startTime: Long, endTime: Long, intervalMinutes: Long)
}
