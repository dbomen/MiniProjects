"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.RequestSeverity = void 0;
exports.isCritical = isCritical;
// don't touch above this line
var RequestSeverity;
(function (RequestSeverity) {
    RequestSeverity[RequestSeverity["Low"] = 0] = "Low";
    RequestSeverity[RequestSeverity["Medium"] = 1] = "Medium";
    RequestSeverity[RequestSeverity["High"] = 2] = "High";
    RequestSeverity[RequestSeverity["Critical"] = 3] = "Critical";
})(RequestSeverity || (exports.RequestSeverity = RequestSeverity = {}));
function isCritical(request) {
    return (request.severity === RequestSeverity.Critical);
}
// TEST
console.log(isCritical({
    id: "t-331",
    severity: RequestSeverity.Low,
    description: "Minor UI glitch",
}) + " == false");
console.log(isCritical({
    id: "t-102",
    severity: RequestSeverity.Medium,
    description: "Slow response times",
}) + " == false");
console.log(isCritical({
    id: "t-004",
    severity: RequestSeverity.Critical,
    description: "Bear in the server room",
}) + " == true");
