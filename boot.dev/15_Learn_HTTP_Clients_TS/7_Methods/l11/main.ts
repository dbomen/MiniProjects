import type { UserProfile } from "./types";

async function deleteUser(
    baseURL: string,
    id: string,
    apiKey: string,
): Promise<void> {
    const fullURL = `${baseURL}/${id}`;

    let res = await fetch(fullURL, {
        method: "DELETE",
        headers: {
            "X-API-Key": apiKey,
        },
        mode: "cors",
    });
    return res.json();
}

// don't touch below this line

const userId = "0194fdc2-fa2f-4cc0-81d3-ff12045b73c8";
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
    console.log("Logging user records:");
    for (const user of users) {
        console.log(
            `User name: ${user.user.name}, Role: ${user.role}, experience: ${user.experience}, Remote: ${user.remote}`,
        );
    }
}

const users = await getUsers(url, generatedKey);
logUsers(users);
console.log("---");

await deleteUser(url, userId, generatedKey);
console.log(`Deleted user with id: ${userId}`);
console.log("---");

const newUsers = await getUsers(url, generatedKey);
logUsers(newUsers);
console.log("---");
