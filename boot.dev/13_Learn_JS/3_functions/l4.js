function getMonthlyPrice(tier) {

    switch (tier) {

        case "basic": return 10000;
        case "premium": return 15000;
        case "enterprise": return 50000;
        default: return 0
    }
}

// don't touch below this line

export { getMonthlyPrice };
