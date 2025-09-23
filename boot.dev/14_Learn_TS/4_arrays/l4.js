"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.formatLabels = formatLabels;
function formatLabels() {
    var labels = [];
    for (var _i = 0; _i < arguments.length; _i++) {
        labels[_i] = arguments[_i];
    }
    if (labels.length == 0)
        return "No Labels";
    if (labels.length == 1)
        return "Label: ".concat(labels[0]);
    var sb = "Labels: ";
    for (var i = 0; i < labels.length; i++) {
        if (i != 0)
            sb += ", ";
        sb += labels[i];
    }
    return sb;
}
// TEST
console.log(formatLabels("urgent", "critical", "important"));
console.log(formatLabels("wontfix"));
console.log(formatLabels());
