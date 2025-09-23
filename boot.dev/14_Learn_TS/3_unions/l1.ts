export function getTicketInfo(id: number | string) {

    if (typeof id === "string") {
        id = parseInt(id.substring(8), 10);
    }

    return `Processing ticket: ${id}`;
}

// TEST
console.log(getTicketInfo(42));
console.log(getTicketInfo("SUPPORT-42"));
