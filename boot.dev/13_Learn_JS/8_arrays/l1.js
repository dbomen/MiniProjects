const sentMessages = [];
sentMessages.push("Welcome to Textio!");
logArray(sentMessages);
sentMessages.push("Reminder: Your payment is due soon.");
logArray(sentMessages);

// don't touch below this line

function logArray(arr) {
    console.log("Array contents:");
    for (const el of arr) {
        console.log(` - ${el}`);
    }
    console.log("======================================");
}
