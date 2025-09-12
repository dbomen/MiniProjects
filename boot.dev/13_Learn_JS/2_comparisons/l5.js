const retryLimit = 10;
const numRetries = 9;

// don't touch above this line

const messageStatus = (numRetries < retryLimit) ? "Processing" : "Failed";

// don't touch below this line

console.log(messageStatus);
