const bootdevAPIDomain = "api.boot.dev";

// don't touch below this line

import type { Issue } from "./types";

const apiKey = generateKey();
const issues = await getIssueData(bootdevAPIDomain);

logIssues(issues);

async function getIssueData(domain: string): Promise<Issue[]> {
    const response = await fetch(
        `https://${domain}/v1/courses_rest_api/learn-http/issues`,
        {
            method: "GET",
            mode: "cors",
            headers: {
                "X-API-Key": apiKey,
                "Content-Type": "application/json",
            },
        },
    );
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

function logIssues(issues: Issue[]): void {
    for (const issue of issues) {
        console.log(issue.title);
    }
}
