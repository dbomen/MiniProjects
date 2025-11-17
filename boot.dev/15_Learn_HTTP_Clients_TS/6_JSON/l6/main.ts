function parseProject(projectString: string): void {

    try {
        const parsedJson = JSON.parse(projectString);
        printProjectObj(parsedJson);
    } catch (error) {
        console.log("invalid json string");
    }
}

// don't touch below this line

import type { Project } from "./types";

function printProjectObj(parsed: Project) {
    console.log(`id: ${parsed.id}`);
    console.log(`completed: ${parsed.completed}`);
    console.log(`title: ${parsed.title}`);
    console.log(`assignees: ${parsed.assignees}`);
}

parseProject(`
{
  "completed": false,
  "id": "0194fdc2-fa2f-4cc0-81d3-ff12045b73c8",
  "title": "Unfidget the widget",
  "assignees": 14
}
`);

console.log("---");

parseProject(`
{
  "completed":true,
  "id":"2f8282cb-e2f9-496f-b144-c0aa4ced56db",
  "title":"Re-spaghettify the codebase - things broke",
  "assignees": 2
}
`);

console.log("---");

parseProject(`
{
  "completed": false,
  "id":"0f12951e-0a74-4846-a1e0-10b33b13112f"
  "title":"Report quarterly earnings",
  "assignees": 1
}
`);
