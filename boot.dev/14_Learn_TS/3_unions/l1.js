"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.getTicketInfo = getTicketInfo;
function getTicketInfo(id) {
    if (typeof id === "string") {
        id = parseInt(id.substring(8), 10);
    }
    return "Processing ticket: ".concat(id);
}
// TEST
console.log(getTicketInfo(42));
console.log(getTicketInfo("SUPPORT-42"));
