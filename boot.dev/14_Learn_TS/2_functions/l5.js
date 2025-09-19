"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.greetCustomer = greetCustomer;
exports.farewellCustomer = farewellCustomer;
// ?
function greetCustomer(name) {
    return "Hello ".concat(name, ", welcome to Support.ai! How can I assist you today?");
}
function farewellCustomer(name) {
    return "Goodbye ".concat(name, ", have a great day!");
}
