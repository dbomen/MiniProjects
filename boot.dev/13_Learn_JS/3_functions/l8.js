function printReports(intro, body, outro) {

    printCostReport(m => 2 * m.length, intro);
    printCostReport(m => 3 * m.length, body);
    printCostReport(m => 4 * m.length, outro);
}

// don't touch below this line

function printCostReport(costCalculator, message) {
    const cost = costCalculator(message);
    console.log(`Message: "${message}" Cost: ${cost} cents`);
}

printReports(
    "Welcome to the Hotel California",
    "Such a lovely place",
    "Plenty of room at the Hotel California",
);
