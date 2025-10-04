export type OrderData = {
    id: string;
    accountType: "free" | "premium";
    amount: number;
    contact: {
        email: string;
        phone: string;
    };
};

export function handleSuccessfulOrder(orderResponse: unknown): string {
    const { accountType, contact } = orderResponse as OrderData;

    let welcome: string;
    switch (accountType) {
        case "free":
            welcome = "Welcome to Support.ai!";
            break;
        case "premium":
            welcome = "Thanks for giving us money!";
            break;
    }

    return `To ${contact.email}: ${welcome}`;
}
