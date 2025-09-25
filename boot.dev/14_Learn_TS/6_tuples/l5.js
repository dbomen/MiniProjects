"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.formatTicket = formatTicket;
// don't touch above this line
function formatTicket(ticket) {
    var id = ticket[0], comment = ticket[1], label = ticket[2];
    return "#".concat(id, " ").concat(comment, " [").concat(label, "]");
}
// TEST
console.log(formatTicket([1, "Can't turn off capslock for login", "WONTFIX"]));
console.log(formatTicket([2, "Electrical outlet missing for charging", "CRIT"]));
