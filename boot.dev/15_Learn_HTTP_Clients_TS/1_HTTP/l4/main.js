const testURL = "http://why.wont.it.fetch";
const issueURL = "https://api.boot.dev/v1/courses_rest_api/learn-http/issues";
const issues = await getData(issueURL);
logIssues(issues);
// don't touch below this line
async function getData(url) {
    const apiKey = generateKey();
    const response = await fetch(url, {
        method: "GET",
        mode: "cors",
        headers: {
            "X-API-Key": apiKey,
            "Content-Type": "application/json",
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
function logIssues(issues) {
    for (const issue of issues) {
        console.log(issue.title);
    }
}
export {};
