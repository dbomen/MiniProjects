function getMessageStatus(message) {
    let messageStatus = "processing";

    function isValidLength(message) {
        messageStatus = "invalid";

        if (message.length > 0) {
            messageStatus = "valid";
        }
    }

    // don't touch above this line

    isValidLength(message);
    return messageStatus;
}

// don't touch below this line

export { getMessageStatus };
