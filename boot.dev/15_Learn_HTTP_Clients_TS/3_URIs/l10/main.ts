const url = "https://api.boot.dev/v1/courses_rest_api/learn-http/projects";

// don't touch below this line

import type { Project } from "./types";

const apiKey = generateKey();
const response = await fetch(url, {
    method: "GET",
    mode: "cors",
    headers: {
        "X-API-Key": apiKey,
        "Content-Type": "application/json",
    },
});

const responseData = await response.json();

logProjects(responseData);

function generateKey(): string {
    const characters = "ABCDEF0123456789";
    let result = "";
    for (let i = 0; i < 16; i++) {
        result += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return result;
}
function logProjects(projects: Project[]) {
    for (const project of projects) {
        console.log(`Project: ${project.title}, Completed: ${project.completed}`);
    }
}
