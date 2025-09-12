function bulkSendCost(numMessages) {

    let sum = 0;
    for (let i = 0; i < numMessages; i++) {

        sum += 1 + i * 0.01;
    }

    return sum;
}

// TEST
console.log(bulkSendCost(10) + " == " + 10.45);
console.log(bulkSendCost(20) + " == " + 21.9);

export { bulkSendCost };
