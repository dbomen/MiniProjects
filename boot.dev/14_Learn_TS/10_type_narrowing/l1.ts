type RegularCustomer = {
    plan: "regular";
    tickets: number;
    aboveLimit: boolean;
};

type PremiumCustomer = {
    plan: "premium";
    tickets: number;
};

export type Customer = RegularCustomer | PremiumCustomer;

export function openTicket(customer: Customer): number {
    if (customer.plan === "regular" && customer.aboveLimit) {
        return -1;
    }
    return customer.tickets + 1;
}
