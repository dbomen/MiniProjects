"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.createLogEntry = createLogEntry;
// ?
function createLogEntry(message, source) {
    return "[".concat(source, "] LOG - ").concat(message);
}
// TEST
console.log(createLogEntry("info: User logged in", "api_123") + " == [api_123] LOG - info: User logged in");
console.log(createLogEntry("warn: Database connection unstable", "database_456") + " == [database_456] LOG - warn: Database connection unstable");
