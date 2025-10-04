"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.openTicket = openTicket;
function openTicket(customer) {
    if (customer.plan === "regular" && customer.aboveLimit) {
        return -1;
    }
    return customer.tickets + 1;
}
