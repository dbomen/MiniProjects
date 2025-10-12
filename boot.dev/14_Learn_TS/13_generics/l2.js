"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.pair = pair;
function pair(a, b) {
    var out = [];
    for (var i = 0; i < Math.min(a.length, b.length); i++) {
        var tuple = [a[i], b[i]];
        out.push(tuple);
    }
    return out;
}
console.log(pair(["Bilbo"], ["hungry", "sleepy"]));
console.log(pair(["Aragorn", "Boromir"], [1]));
console.log(pair([], ["lonely"]));
