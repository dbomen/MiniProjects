const generatedApiKey = generateKey();
const url = "https://api.boot.dev/v1/courses_rest_api/learn-http/projects/52fdfc07-2182-454f-963f-5f0f9a621d72";
const newProjectData = {
    completed: false,
    id: "52fdfc07-2182-454f-963f-5f0f9a621d72",
    title: "Product Roadmap 2025",
    assignees: 1,
};
const oldProject = await getProjectResponse(generatedApiKey, url);
console.log("Got old project:");
console.log(`- title: ${oldProject.title}, assignees: ${oldProject.assignees}`);
console.log("---");
await putProject(generatedApiKey, url, newProjectData);
console.log("Project updated!");
console.log("---");
const newProject = await getProjectResponse(generatedApiKey, url);
console.log("Got new project:");
console.log(`- title: ${newProject.title}, assignees: ${newProject.assignees}`);
console.log("---");
// don't touch below this line
async function getProjectResponse(apiKey, url) {
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
async function putProject(apiKey, url, data) {
    const response = await fetch(url, {
        method: "PUT",
        mode: "cors",
        headers: {
            "X-API-Key": apiKey,
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
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
