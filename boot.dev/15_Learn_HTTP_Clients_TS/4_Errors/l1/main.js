try {
    printUserExperience(4);
}
catch (err) {
    if (err instanceof Error) {
        console.log(err);
    }
}
try {
    printUserExperience(10);
}
catch (err) {
    if (err instanceof Error) {
        console.log(err);
    }
}
try {
    printUserExperience(200);
}
catch (err) {
    if (err instanceof Error) {
        console.log(err);
    }
}
// don't touch below this line
function printUserExperience(level) {
    if (level < 1 || level > 100) {
        throw new Error("Experience level must be between 1 and 100!");
    }
    console.log(`The user has ${level} years of experience!`);
}
export {};
