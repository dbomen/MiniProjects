const url = "https://api.boot.dev/v1/courses_rest_api/learn-http/users";
async function getUserById(url, id) {
    const path = `${url}/${id}`;
    const response = await safeFetch(path, {
        method: "GET",
        mode: "cors",
        headers: {
            "X-API-Key": apiKey,
            "Content-Type": "application/json",
        },
    });
    return response.json();
}
// don't touch below this line
const uuid = "2f8282cb-e2f9-496f-b144-c0aa4ced56db";
const apiKey = generateKey();
function generateKey() {
    const characters = "ABCDEF0123456789";
    let result = "";
    for (let i = 0; i < 16; i++) {
        result += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return result;
}
function logUser(user) {
    console.log(`User Info - UUID: ${user.id}, Name: ${user.user.name}, Role: ${user.role}, Experience: ${user.experience}, Remote: ${user.remote}`);
}
const user = await getUserById(url, uuid);
logUser(user);
function safeFetch(url, options) {
    const parsedUrl = new URL(url);
    if (parsedUrl.protocol !== "https:") {
        throw new Error("Insecure request. Use HTTPS protocol.");
    }
    return fetch(url, options);
}
export {};
