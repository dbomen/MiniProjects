function updateMessageStatus(messageId, currentStatus, isDelivered) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (currentStatus == "Sending" && isDelivered)
                resolve(`Textio Message ${messageId} has been delivered successfully.`);
            else if (currentStatus == "Sending" && !isDelivered)
                reject(`Textio Message ${messageId} is still sending and cannot be marked as delivered.`);
            else
                resolve(`Textio Message ${messageId} status updated to ${currentStatus}.`);
        }, 500);
    });
}

// TEST
updateMessageStatus("M123", "Sending", true)
    .then((message) => console.log(message))
    .catch((message) => console.log(message));

updateMessageStatus("M456", "Sending", false)
    .then((message) => console.log(message))
    .catch((message) => console.log(message));

// don't touch below this line

export { updateMessageStatus };
