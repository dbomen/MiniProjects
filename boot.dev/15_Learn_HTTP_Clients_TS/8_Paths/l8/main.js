async function fetchTasks(baseURL, availability) {
    let limit;
    if (availability == "Low")
        limit = 1;
    else if (availability == "Medium")
        limit = 3;
    else
        limit = 5;
    const fullURL = `${baseURL}?sort=estimate&limit=${limit}`;
    return getIssues(fullURL, apiKey);
}
// don't touch below this line
const url = "https://api.boot.dev/v1/courses_rest_api/learn-http/issues";
const apiKey = generateKey();
const lowIssues = await fetchTasks(url, "Low");
console.log("Getting issues for low availability user...");
for (const issue of lowIssues) {
    console.log(`Issue: ${issue.title} - estimate: ${issue.estimate}`);
}
console.log("---");
const mediumIssues = await fetchTasks(url, "Medium");
console.log("Getting issues for medium availability user...");
for (const issue of mediumIssues) {
    console.log(`Issue: ${issue.title} - estimate: ${issue.estimate}`);
}
console.log("---");
console.log("Getting issues for high availability user...");
const highIssues = await fetchTasks(url, "High");
for (const issue of highIssues) {
    console.log(`Issue: ${issue.title} - estimate: ${issue.estimate}`);
}
async function getIssues(url, apiKey) {
    const response = await fetch(url, {
        method: "GET",
        mode: "cors",
        headers: {
            "X-API-Key": apiKey,
        },
    });
    return response.json();
}
function generateKey() {
    const characters = "ABCDEF0123456789";
    let result = "";
    for (let i = 0; i < 16; i++) {
        result += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return result;
}
export {};
