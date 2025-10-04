"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.parseCustomerMessage = parseCustomerMessage;
function parseCustomerMessage(input) {
    if (typeof input === "string") {
        return {
            content: input,
            source: "email",
        };
    }
    else if (Array.isArray(input)) {
        return {
            content: input.join("\n"),
            source: "chat",
        };
    }
    else {
        return {
            content: "",
            source: "unknown",
        };
    }
}
// TEST
console.log(parseCustomerMessage("Hello?\nI need some help.\nDo you know how to fix my printer?"));
console.log(parseCustomerMessage(["Hi", "In case you don't know...", "YOUR PRODUCT SUCKS"]));
console.log(parseCustomerMessage({
    hiddenMessage: "Through the darkness of future's past, the magician longs to see.",
}));
