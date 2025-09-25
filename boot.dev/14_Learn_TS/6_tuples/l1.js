"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.createTicket = createTicket;
function createTicket(prevTicket, comment) {
    return [prevTicket + 1, comment, comment.toLowerCase().includes("critical")];
}
// TEST
console.log(createTicket(0, "Critical help needed"));
console.log(createTicket(1, "Salmon is missing"));
