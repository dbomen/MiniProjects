const issues = await getIssueData();
logIssues(issues);
// don't touch below this line
async function getIssueData() {
    const apiKey = generateKey();
    console.log(apiKey);
    const response = await fetch("https://api.boot.dev/v1/courses_rest_api/learn-http/issues", {
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
