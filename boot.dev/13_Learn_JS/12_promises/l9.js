async function getMessageHash(sender, content) {
    await new Promise(resolve => setTimeout(resolve, 100));

    const hash = await sha256Hex(content);
    return `Sender (${sender}): ${hash}`;
}

// TEST
const promise = getMessageHash("Ballan", "So what are we having for lunch");
promise.then(val => console.log(val));
console.log("Me first?");

// don't touch below this line

async function sha256Hex(str) {
    const encoder = new TextEncoder();
    const data = encoder.encode(str);
    const hashBuffer = await crypto.subtle.digest("SHA-256", data);
    const hashArray = Array.from(new Uint8Array(hashBuffer));
    const hex = hashArray.map((b) => b.toString(16).padStart(2, "0")).join("");
    return hex;
}

export { getMessageHash };
