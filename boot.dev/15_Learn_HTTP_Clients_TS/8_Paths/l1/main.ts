import type { Resource } from "./types";

async function getResources(path: string): Promise<Resource[]> {
    const fullURL = `https://api.boot.dev` + path;

    const response = await fetch(fullURL, {
        method: "GET",
        mode: "cors",
        headers: {
            "X-API-Key": generateKey(),
            "Content-Type": "application/json",
        },
    });
    return response.json();
}

// don't touch below this line

const projects = await getResources("/v1/courses_rest_api/learn-http/projects");
console.log("Projects:");
logResources(projects);
console.log(" --- ");

const issues = await getResources("/v1/courses_rest_api/learn-http/issues");
console.log("Issues:");
logResources(issues);
console.log(" --- ");

const users = await getResources("/v1/courses_rest_api/learn-http/users");
console.log("Users:");
logResources(users);

function logResources(resources: Resource[]) {
    for (const resource of resources) {
        console.log(` - ${JSON.stringify(resource)}`);
    }
}

function generateKey(): string {
    const characters = "ABCDEF0123456789";
    let result = "";
    for (let i = 0; i < 16; i++) {
        result += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return result;
}
