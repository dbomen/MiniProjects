"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.logSystemEvent = logSystemEvent;
function logSystemEvent(event, severity) {
    return "SYSTEM ".concat(severity.toUpperCase(), ": ").concat(event);
}
console.log(logSystemEvent("EVENT 1", "error"));
