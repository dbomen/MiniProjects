export function createTicket(
    prevTicket: number,
    comment: string,
): [number, string, boolean] {

    return [prevTicket + 1, comment, comment.toLowerCase().includes("critical")];
}

// TEST
console.log(createTicket(0, "Critical help needed"));
console.log(createTicket(1, "Salmon is missing"));
