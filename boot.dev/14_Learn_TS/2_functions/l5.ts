export type SupportResponse = (name: string) => string;

// ?

export function greetCustomer(name: string) {
    return `Hello ${name}, welcome to Support.ai! How can I assist you today?`;
}

export function farewellCustomer(name: string) {
    return `Goodbye ${name}, have a great day!`;
}
