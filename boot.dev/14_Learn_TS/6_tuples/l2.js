"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.resolveTicket = resolveTicket;
// don't touch above this line
function resolveTicket(ticket) {
    return [ticket[0], ticket[1], ticket[2], true];
}
// TEST
console.log(resolveTicket([1, "Delivery package missing", true, false]));
