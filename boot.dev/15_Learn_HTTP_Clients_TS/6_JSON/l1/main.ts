import type { Project } from "./types";

async function getProjects(): Promise<Project[]> {
    const url = "https://api.boot.dev/v1/courses_rest_api/learn-http/projects";
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

// Don't touch below this line

const apiKey = generateKey();

const projects = await getProjects();
console.log("Got some projects from the server.");
for (const project of projects) {
    console.log(`- title: ${project.title}, assignees: ${project.assignees}`);
}

function generateKey(): string {
    const characters = "ABCDEF0123456789";
    let result = "";
    for (let i = 0; i < 16; i++) {
        result += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return result;
}
