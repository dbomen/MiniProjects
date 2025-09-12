async function processAnalytics(data) {
    let analysis = "";

    return new Promise((resolve) => {
        setTimeout(() => {
            resolve(analysis);
        }, 100);

        setTimeout(() => {
            analysis += " - Finished!";
        }, 0);

        // don't touch above this line

        new Promise((resolve) => resolve())
            .then(() => analysis += `- Processing: ${data}`);

        // don't touch below this line

        analysis += "Analyzing...";
    });
}

// TEST
const analysis = await processAnalytics("Excuse me can I talk to you for a minute");
console.log(analysis);

export { processAnalytics };
