export type Ticket = readonly [id: number, comment: string, label: string];

// don't touch above this line

export function formatTicket(ticket: Ticket) {
    const [id, comment, label] = ticket;
    return `#${id} ${comment} [${label}]`;
}

// TEST
console.log(formatTicket([1, "Can't turn off capslock for login", "WONTFIX"]));
console.log(formatTicket([2, "Electrical outlet missing for charging", "CRIT"]));
