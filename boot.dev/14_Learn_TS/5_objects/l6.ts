export function findNumUniqueLabels(formattedAddresses: string[]) {

    let set: Set<string> = new Set<string>(formattedAddresses);
    return set.size;
}

// TEST
console.log(findNumUniqueLabels(["spam", "urgent", "updates", "spam"]) + " == 3");
console.log(findNumUniqueLabels(["promo", "promo", "promo", "promo"]) + " == 1");
