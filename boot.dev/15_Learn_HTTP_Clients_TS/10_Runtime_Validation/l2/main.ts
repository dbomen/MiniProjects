import { z } from "zod";

export const JelloIssueSchema = z.object({
    id: z.string(),
    title: z.string().min(1)
});
