export type Ticket = readonly [id: number, comment: string, label?: string];

export function formatTicket(ticket: Ticket): string {
    const [id, comment, label] = ticket;

    if (label === undefined) return `#${id ? id : ""} ${comment}`;
    else return `#${id ? id : ""} ${comment} [${label}]`;
}

// TEST
console.log(formatTicket([1, "My cans of tuna fish are missing"]));
console.log(formatTicket([2, "Cats are everywhere!", "WONTFIX"]));
console.log(formatTicket([3, "Send a dog"]));
console.log(formatTicket([4, "Give me my tuna!", "REFUND"]));
