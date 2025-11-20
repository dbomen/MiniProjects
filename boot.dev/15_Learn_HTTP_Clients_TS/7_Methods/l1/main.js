async function getUsers(url, apiKey) {
    let data = await fetch(url, {
        method: "GET",
        mode: "cors",
        headers: {
            "X-API-Key": apiKey,
        }
    });
    return data.json();
}
// Don't touch below this line
const generatedKey = generateKey();
const url = "https://api.boot.dev/v1/courses_rest_api/learn-http/users";
const users = await getUsers(url, generatedKey);
logUsers(users);
function generateKey() {
    const characters = "ABCDEF0123456789";
    let result = "";
    for (let i = 0; i < 16; i++) {
        result += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return result;
}
function logUsers(users) {
    for (const user of users) {
        console.log(`User name: ${user.user.name}, Role: ${user.role}, experience: ${user.experience}, Remote: ${user.remote}`);
    }
}
export {};
