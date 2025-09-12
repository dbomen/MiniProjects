const notification = {
    notify(recipient, message) {
        return `Notification for ${recipient}: ${message}`;
    },
};

const systemNotification = Object.create(notification);
systemNotification.broadcast = (message) => {

    return `Broadcast to all users: ${message}`;
}

// TEST
console.log(systemNotification.notify("john@example.com", "Scheduled maintenance at midnight."));
console.log(systemNotification.broadcast("System update available tonight."));

// don't touch below this line

export { notification, systemNotification };
