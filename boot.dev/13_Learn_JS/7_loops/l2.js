function maxMessagesWithinBudget(budget) {

    let sum = 0;
    let i = 0;
    for (; true; i++) {

        const cost = 1.0 + i * 0.01;
        if (sum + cost > budget) break;
        sum += cost;
    }

    return i;
}

// TEST
console.log(maxMessagesWithinBudget(5.0) + " == " + 4);
console.log(maxMessagesWithinBudget(10.0) + " == " + 9);
console.log(maxMessagesWithinBudget(0.99) + " == " + 0);
console.log(maxMessagesWithinBudget(1.0) + " == " + 1);

export { maxMessagesWithinBudget };
