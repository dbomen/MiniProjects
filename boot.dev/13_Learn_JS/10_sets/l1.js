function deduplicateEmails(emails) {
    const set = new Set(emails);
    return Array.from(set);
}

// TEST
console.log(deduplicateEmails(
    [
        "wayne.lagner@dev.boot",
        "heckmann@what.de",
        "heckmann@what.de",
        "a.liar@pants.fire",
    ]));

// don't touch below this line

export { deduplicateEmails };
