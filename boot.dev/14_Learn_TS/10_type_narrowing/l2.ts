export type CustomerMessage = {
    content: string;
    source: "chat" | "email" | "unknown";
}

export function parseCustomerMessage(input: unknown): CustomerMessage {

    if (typeof input === "string") {
        return {
            content: input,
            source: "email",
        } as CustomerMessage;
    }
    else if (Array.isArray(input)) {
        return {
            content: input.join("\n"),
            source: "chat",
        } as CustomerMessage;
    }
    else {
        return {
            content: "",
            source: "unknown",
        }
    }
}

// TEST
console.log(parseCustomerMessage(`Hello?
I need some help.
Do you know how to fix my printer?`));
console.log(parseCustomerMessage(["Hi", "In case you don't know...", "YOUR PRODUCT SUCKS"]));
console.log(parseCustomerMessage({
    hiddenMessage:
        "Through the darkness of future's past, the magician longs to see.",
}));
