export function findNumUniqueLabels(formattedAddresses) {
    let set = new Set(formattedAddresses);
    return set.size;
}
// TEST
console.log(findNumUniqueLabels(["spam", "urgent", "updates", "spam"]) + " == 3");
console.log(findNumUniqueLabels(["promo", "promo", "promo", "promo"]) + " == 1");
