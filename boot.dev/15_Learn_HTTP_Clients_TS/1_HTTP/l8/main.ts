import type { Issue } from "./types";

const url = getURL();
const settings = getSettings();

const response = await fetch(url, settings);
const responseData = await response.json();

logIssues(responseData);

// don't touch below this line

function getSettings(): RequestInit {
    const apiKey = generateKey();
    return {
        method: "GET",
        mode: "cors",
        headers: {
            "X-API-Key": apiKey,
            "Content-Type": "application/json",
        },
    };
}

function getURL(): string {
    return "https://api.boot.dev/v1/courses_rest_api/learn-http/issues";
}

function generateKey(): string {
    const characters = "ABCDEF0123456789";
    let result = "";
    for (let i = 0; i < 16; i++) {
        result += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return result;
}

function logIssues(issues: Issue[]): void {
    for (const issue of issues) {
        console.log(issue.title);
    }
}
