export function pair<A, B>(a: A[], b: B[]): [A, B][] {
    let out: [A, B][] = [];
    for (let i = 0; i < Math.min(a.length, b.length); i++) {
        let tuple: [A, B] = [a[i], b[i]];
        out.push(tuple);
    }
    return out;
}

// TEST
type PairableValue = string | number;

console.log(pair<PairableValue, PairableValue>(
    ["Bilbo"],
    ["hungry", "sleepy"]
));
console.log(pair<PairableValue, PairableValue>(
    ["Aragorn", "Boromir"],
    [1],
));
console.log(pair<PairableValue, PairableValue>(
    [],
    ["lonely"],
));
