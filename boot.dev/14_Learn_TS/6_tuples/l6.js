"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.formatTicket = formatTicket;
function formatTicket(ticket) {
    var id = ticket[0], comment = ticket[1], label = ticket[2];
    if (label === undefined)
        return "#".concat(id ? id : "", " ").concat(comment);
    else
        return "#".concat(id ? id : "", " ").concat(comment, " [").concat(label, "]");
}
// TEST
console.log(formatTicket([1, "My cans of tuna fish are missing"]));
console.log(formatTicket([2, "Cats are everywhere!", "WONTFIX"]));
console.log(formatTicket([3, "Send a dog"]));
console.log(formatTicket([4, "Give me my tuna!", "REFUND"]));
