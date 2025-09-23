export function calculateApiCost(numReqs: number, tier?: string) {

    switch (tier) {
        case undefined: return numReqs * 0.1;
        case "pro": return numReqs * 0.05;
        case "enterprise": return numReqs * 0.03;
    }
}

// TEST
console.log(calculateApiCost(100, "pro") + " == 5.0");
console.log(calculateApiCost(100) + " == 10.0");
console.log(calculateApiCost(100, "enterprise") + " == 3.0");
