export type OrderData = {
    id: string;
    accountType: "free" | "premium";
    amount: number;
    contact: {
        email: string;
        phone: string;
    };
};

function sumOrders(orders: OrderData[]): number {

    let sum = 0;
    for (const order of orders) {
        sum += order.amount!
    }

    return sum;
}

export function reportOrders(orders: OrderData[] | null): string {
    return `Total amount for orders: ${sumOrders(orders!)}`;
}

// TEST
console.log(reportOrders(
    [
        {
            id: "19045",
            accountType: "free",
            amount: 0,
            contact: {
                email: "shelly@doubler.com",
                phone: "0123456789",
            },
        },
        {
            id: "28991",
            accountType: "premium",
            amount: 99,
            contact: {
                email: "ben@oneyedjacks.com",
                phone: "0111226789",
            },
        },
        {
            id: "31772",
            accountType: "premium",
            amount: 149,
            contact: {
                email: "sarah@example.com",
                phone: "0555666777",
            },
        },
    ] as OrderData[]) + " == 248");
