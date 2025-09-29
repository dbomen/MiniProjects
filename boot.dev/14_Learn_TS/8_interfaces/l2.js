"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.getEmailDescription = getEmailDescription;
exports.getTextMessageDescription = getTextMessageDescription;
function getEmailDescription(email) {
    return "[EMAIL] ".concat(email.subject, ": ").concat(email.body);
}
function getTextMessageDescription(text) {
    return "[TEXT] ".concat(text.text) + " - Sent via ".concat(text.carrier);
}
