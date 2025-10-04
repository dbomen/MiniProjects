"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.RequestSeverity = void 0;
exports.convertSeverityToLabel = convertSeverityToLabel;
// don't touch above this line
var RequestSeverity;
(function (RequestSeverity) {
    RequestSeverity["Low"] = "LOW";
    RequestSeverity["Medium"] = "MEDIUM";
    RequestSeverity["High"] = "HIGH";
    RequestSeverity["Critical"] = "CRITICAL";
})(RequestSeverity || (exports.RequestSeverity = RequestSeverity = {}));
;
function convertSeverityToLabel(severity) {
    switch (severity) {
        case 0: return "LOW";
        case 1: return "MEDIUM";
        case 2: return "HIGH";
        case 3: return "CRITICAL";
    }
}
// TEST
console.log(convertSeverityToLabel(0) + " == LOW");
console.log(convertSeverityToLabel(3) + " == LOW");
