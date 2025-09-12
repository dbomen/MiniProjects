const getCleanMessages = (messages, badWord) => {

    let newArray = [];
    for (let message of messages) {

        if (!message.toLowerCase().includes(badWord.toLowerCase())) newArray.push(message);
    }

    return newArray;
};

// TEST
console.log(getCleanMessages([
    "I am a man of constant sorrow",
    "I've seen trouble all my days",
    "Parched and dusty",
    "nuthin' a hot bath won't cure",
], "trouble"));

export { getCleanMessages };
