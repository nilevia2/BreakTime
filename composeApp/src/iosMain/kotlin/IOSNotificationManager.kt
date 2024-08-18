
import platform.UIKit.UILocalNotification
import platform.UIKit.UIApplication
import platform.UIKit.scheduleLocalNotification

class IOSNotificationManager : NotificationManager {
    override fun showNotification(title: String, message: String) {
        val notification = UILocalNotification()
        notification.alertTitle = title
        notification.alertBody = message
        UIApplication.sharedApplication.scheduleLocalNotification(notification)
    }
}