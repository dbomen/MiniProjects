function getCleanRank(reviewWords) {

    let badWordsCounter = 0;
    if (reviewWords.includes("dang")) badWordsCounter++;
    if (reviewWords.includes("shoot")) badWordsCounter++;
    if (reviewWords.includes("heck")) badWordsCounter++;

    if (badWordsCounter == 0) return "clean"
    else if (badWordsCounter == 1) return "dirty"
    else return "filthy"
}

// TEST
console.log("dirty == " + getCleanRank(["avril", "lavigne", "has", "best", "dang", "tour"]));
console.log("clean == " + getCleanRank(["what", "a", "bad", "film"]));

export { getCleanRank };
