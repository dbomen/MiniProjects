function getProviderCount(provider, counts) {

    const numberOfPhoneNumbers = counts[provider];

    if (!numberOfPhoneNumbers) return 0;
    else return numberOfPhoneNumbers;
}

const counts = {
    "A": 10,
    "B": 20,
}
console.log(getProviderCount("A", counts));
console.log(getProviderCount("B", counts));
console.log(getProviderCount("C", counts));

// don't touch below this line

export { getProviderCount };
