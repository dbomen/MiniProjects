export interface Issue {
    id: string;
    title: string;
    status: string;
    estimate: number;
}

export type Availability = "Low" | "Medium" | "High";
