"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.getTicketCount = getTicketCount;
function getTicketCount(user) {
    if (user.role === "agent")
        return user.assignedTickets;
    if (user.role === "customer")
        return user.submittedTickets;
    if (user.role === "admin")
        return user.assignedTickets;
    throw new Error("Invalid type!");
}
// TEST
console.log(getTicketCount({ id: 623, role: "agent", assignedTickets: 5 }) + " == 5");
console.log(getTicketCount({ id: 212, role: "admin", assignedTickets: 10 }) + " == 10");
