import type { UserProfile } from "./types";

async function createUser(
    apiKey: string,
    url: string,
    data: UserProfile,
): Promise<UserProfile> {
    let res = await fetch(url, {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-type": "application/json",
            "X-API-Key": apiKey,
        },
        body: JSON.stringify(data),
    });
    return res.json();
}

// Don't touch below this line

const userToCreate: UserProfile = {
    role: "Junior Developer",
    experience: 2,
    remote: true,
    user: {
        name: "Dan",
        location: "NOR",
        age: 29,
    },
};

const generatedKey = generateKey();
const url = "https://api.boot.dev/v1/courses_rest_api/learn-http/users";

async function getUsers(url: string, apiKey: string): Promise<UserProfile[]> {
    const response = await fetch(url, {
        method: "GET",
        mode: "cors",
        headers: {
            "X-API-Key": apiKey,
        },
    });
    return response.json();
}

function generateKey(): string {
    const characters = "ABCDEF0123456789";
    let result = "";
    for (let i = 0; i < 16; i++) {
        result += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return result;
}

function logUsers(users: UserProfile[]) {
    for (const user of users) {
        console.log(
            `User name: ${user.user.name}, Role: ${user.role}, experience: ${user.experience}, Remote: ${user.remote}`,
        );
    }
}

console.log("Retrieving user data...");
const userDataFirst = await getUsers(url, generatedKey);
logUsers(userDataFirst);
console.log("---");

console.log("Creating new user account...");
const creationResponse = await createUser(generatedKey, url, userToCreate);
console.log(`Creation response body: ${JSON.stringify(creationResponse)}`);
console.log("---");

console.log("Retrieving user data...");
const userDataSecond = await getUsers(url, generatedKey);
logUsers(userDataSecond);
console.log("---");
