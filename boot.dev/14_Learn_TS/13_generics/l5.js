"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.summarizeFeedback = summarizeFeedback;
function summarizeFeedback(data) {
    return transform(data, function (item) { return item.text; });
}
// don't touch below this line
function transform(inputs, fn) {
    var result = [];
    for (var _i = 0, inputs_1 = inputs; _i < inputs_1.length; _i++) {
        var item = inputs_1[_i];
        result.push(fn(item));
    }
    return result;
}
// TEST
console.log(summarizeFeedback([{ id: "abigail", text: "More purple chests please." }]));
console.log(summarizeFeedback([
    { id: "demetrius", text: "Greenhouse works great." },
    { id: "leah", text: "Too many slimes near the cabin." },
]));
console.log(summarizeFeedback([
    { id: "sebastian", text: "Needs more rain." },
    { id: "emily", text: "Too many geodes." },
    { id: "penny", text: "Library hours unclear." },
]));
