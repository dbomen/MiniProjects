"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.calculateTotal = calculateTotal;
function calculateTotal(price, quantity, discount) {
    return price * quantity * (1 - discount);
}
