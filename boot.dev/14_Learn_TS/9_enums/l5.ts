export type ApiTier = "Free" | "Basic" | "Pro" | "Enterprise";

export function getRateLimitForTier(tier: ApiTier) {
    switch (tier) {
        case "Free":
            return 10;
        case "Basic":
            return 100;
        case "Pro":
            return 1000;
        case "Enterprise":
            return 10000;
    }
}
