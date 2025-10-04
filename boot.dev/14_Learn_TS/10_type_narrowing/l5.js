"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.activateModel = activateModel;
function isModelSkippity(model) {
    return ("search" in model &&
        (model.version === "3.5" || model.version === "4" || model.version === "4s"));
}
function isModelJean(model) {
    return ("think" in model &&
        (model.version === "2" || model.version === "3" || model.version === "3.14"));
}
function activateModel(model) {
    if (isModelSkippity(model))
        return "Activated model Skippity version ".concat(model.version, " with searching ").concat(model.search);
    else
        return "Activated model Skippity version ".concat(model.version, " with thinking ").concat(model.think);
}
