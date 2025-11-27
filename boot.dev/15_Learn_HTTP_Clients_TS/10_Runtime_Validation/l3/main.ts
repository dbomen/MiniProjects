import { z } from "zod";

const JelloIssueSchema = z.object({
    id: z.string(),
    title: z.string().min(1)
});

export type JelloIssue = z.infer<typeof JelloIssueSchema>;

export async function fetchJelloIssue(): Promise<JelloIssue[]> {
    const res = await fetch("https://api.boot.dev/v1/courses_rest_api/learn-http/projects");
    const data = await res.json();

    JelloIssueSchema.array().parse(data);

    return data;
}

fetchJelloIssue();
