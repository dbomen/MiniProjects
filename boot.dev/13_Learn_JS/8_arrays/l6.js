function splitLogs(logs, slug) {

    for (let i = 0; i < logs.length; i++) {

        const log = logs[i];
        if (log.includes(slug)) {

            return {

                before: logs.slice(0, i),
                after: logs.slice(i + 1),
                i: i,
            }
        }
    }

    return {

        before: [],
        after: [],
        i: -1,
    }
}

// TEST
console.log(splitLogs(
    [
        "error at line 10",
        "warning at line 15",
        "the dev who wrote line 21 should be fired",
        "debug info",
        "error at line 20",
        "user login",
    ],
    "debug"));

export { splitLogs };
