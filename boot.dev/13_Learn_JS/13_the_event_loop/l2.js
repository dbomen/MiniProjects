async function sleep(ms) {

    try {
        const promise = await new Promise(resolve => setTimeout(resolve, ms));
        return promise;
    }
    catch (err) {
        throw new Error(err);
    }
}

// TEST
const ms = 1000;
await sleep(ms);
console.log(`Printed after ${ms / 1000} seconds`);

// don't touch below this line

export { sleep };
