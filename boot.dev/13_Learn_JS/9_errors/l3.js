function cleanup() {
    try {
        throw new Error("Textio processing failed");
    } catch (err) {
        throw new Error("Error in catch block");
    } finally {
        console.log("Cleanup complete");
    }
}

// don't touch below this line

try {
    cleanup();
} catch (err) {
    console.log(err.message);
}
