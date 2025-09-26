export type SupportAgent = {
    id: number;
    role: "agent";
    assignedTickets: number;
};

export type EndUser = {
    id: number;
    role: "customer";
    submittedTickets: number;
};

// don't touch above this line

export type SupportAdmin = {
    id: number;
    role: "admin";
    assignedTickets: number;
};

export type SupportAgentUser = SupportAgent | EndUser | SupportAdmin;

export function getTicketCount(user: SupportAgentUser): number {
    if (user.role === "agent") return user.assignedTickets;
    if (user.role === "customer") return user.submittedTickets;
    if (user.role === "admin") return user.assignedTickets;

    throw new Error("Invalid type!");
}

// TEST
console.log(getTicketCount(
    { id: 623, role: "agent", assignedTickets: 5 } as SupportAgentUser) + " == 5");
console.log(getTicketCount(
    { id: 212, role: "admin", assignedTickets: 10 } as SupportAgentUser) + " == 10");
