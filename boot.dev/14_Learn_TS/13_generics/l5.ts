interface HasText {
    text: string
}

export function summarizeFeedback<T extends HasText>(
    data: T[],
): string[] {
    return transform(data, item => item.text);
}

// don't touch below this line

function transform<T, R>(inputs: T[], fn: (item: T) => R): R[] {
    const result: R[] = [];
    for (const item of inputs) {
        result.push(fn(item));
    }
    return result;
}

// TEST
console.log(summarizeFeedback(
    [{ id: "abigail", text: "More purple chests please." }]
));

console.log(summarizeFeedback(
    [
        { id: "demetrius", text: "Greenhouse works great." },
        { id: "leah", text: "Too many slimes near the cabin." },
    ]
));

console.log(summarizeFeedback(
    [
        { id: "sebastian", text: "Needs more rain." },
        { id: "emily", text: "Too many geodes." },
        { id: "penny", text: "Library hours unclear." },
    ]
));
