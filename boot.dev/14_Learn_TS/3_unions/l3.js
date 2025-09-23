"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.estimateResponseTime = estimateResponseTime;
function estimateResponseTime(promptLength, modelType) {
    if (promptLength === void 0) { promptLength = 100; }
    if (modelType === void 0) { modelType = "text"; }
    switch (modelType) {
        case "text": return Math.round(2 + (0.01 * promptLength));
        case "image": return Math.round(5 + (0.02 * promptLength));
        case "code": return Math.round(3 + (0.05 * promptLength));
        default: return -1;
    }
}
// TEST
console.log(estimateResponseTime() + "== 3");
console.log(estimateResponseTime(200) + "== 4");
console.log(estimateResponseTime(150, "image") + "== 8");
