function main() {
    try {
        logObject(getMessageRecord(1));
        logObject(getMessageRecord(2));
        logObject(getMessageRecord(3));
        logObject(getMessageRecord(4));
    } catch (err) {
        console.log(err.message);
    }
}

// don't touch below this line

function getMessageRecord(messageId) {
    if (messageId === 1) {
        return { content: "Welcome to Textio!", timestamp: "2025-01-01T12:00:00Z" };
    }
    if (messageId === 2) {
        return {
            content: "Your order has shipped",
            timestamp: "2025-01-02T12:00:00Z",
        };
    }
    if (messageId === 3) {
        return {
            content: "Reminder: Payment due soon",
            timestamp: "2025-01-03T12:00:00Z",
        };
    }
    throw new Error("text id not found");
}

function logObject(obj) {
    for (const key in obj) {
        console.log(` - ${key}: ${obj[key]}`);
    }
    console.log("---");
}

main();
