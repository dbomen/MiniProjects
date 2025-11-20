import type { UserProfile } from "./types";

async function updateUser(
    baseURL: string,
    id: string,
    data: UserProfile,
    apiKey: string,
): Promise<UserProfile> {
    const fullURL = `${baseURL}/${id}`;

    let res = await fetch(fullURL, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "X-API-Key": apiKey,
        },
        mode: "cors",
        body: JSON.stringify(data),
    });

    return res.json();
}

async function getUserById(
    baseURL: string,
    id: string,
    apiKey: string,
): Promise<UserProfile> {
    const fullURL = `${baseURL}/${id}`;

    let res = await fetch(fullURL, {
        method: "GET",
        headers: {
            "X-API-Key": apiKey,
        },
        mode: "cors",
    });

    return res.json();
}

// don't touch below this line

const userId = "2f8282cb-e2f9-496f-b144-c0aa4ced56db";
const generatedKey = generateKey();
const baseURL = "https://api.boot.dev/v1/courses_rest_api/learn-http/users";

function generateKey(): string {
    const characters = "ABCDEF0123456789";
    let result = "";
    for (let i = 0; i < 16; i++) {
        result += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return result;
}

function logUser(user: UserProfile) {
    console.log(
        `User name: ${user.user.name}, Role: ${user.role}, experience: ${user.experience}, Remote: ${user.remote}`,
    );
}

const userData = await getUserById(baseURL, userId, generatedKey);
logUser(userData);

console.log(`Updating user with id: ${userId}`);
userData.experience = 2;
userData.role = "Junior Developer";
userData.remote = true;
userData.user.name = "Dan";
const updatedUser = await updateUser(baseURL, userId, userData, generatedKey);
logUser(updatedUser);
