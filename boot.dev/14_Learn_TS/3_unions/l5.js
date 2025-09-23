"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.setPriority = setPriority;
function setPriority(level) {
    switch (level) {
        case "low": return 0;
        case "medium": return 1;
        case "high": return 2;
        case "critical": return 3;
        default: return 0;
    }
}
