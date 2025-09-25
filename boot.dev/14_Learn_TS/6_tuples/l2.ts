export type Ticket = readonly [number, string, boolean, boolean];

// don't touch above this line

export function resolveTicket(ticket: Ticket): Ticket {

    return [ticket[0], ticket[1], ticket[2], true];
}

// TEST
console.log(resolveTicket([1, "Delivery package missing", true, false]));
