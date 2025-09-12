const textioSetupCompleteWait = 400;
const errorHandlingWait = 300;
const messageRoutingWait = 200;
const smsProvidersWait = 100;

// don't touch below this line

setTimeout(
    () => console.log("Textio setup complete!"),
    textioSetupCompleteWait,
);
setTimeout(
    () => console.log("Setting up error handling and retries..."),
    errorHandlingWait,
);
setTimeout(
    () => console.log("Configuring message routing..."),
    messageRoutingWait,
);
setTimeout(
    () => console.log("Connecting to SMS providers..."),
    smsProvidersWait,
);

console.log("Starting Textio service initialization...");

await sleep(2500);
function sleep(ms) {
    return new Promise((resolve) => setTimeout(resolve, ms));
}
