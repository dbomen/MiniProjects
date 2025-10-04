"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.handleSuccessfulOrder = handleSuccessfulOrder;
function handleSuccessfulOrder(orderResponse) {
    var _a = orderResponse, accountType = _a.accountType, contact = _a.contact;
    var welcome;
    switch (accountType) {
        case "free":
            welcome = "Welcome to Support.ai!";
            break;
        case "premium":
            welcome = "Thanks for giving us money!";
            break;
    }
    return "To ".concat(contact.email, ": ").concat(welcome);
}
