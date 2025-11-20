export type Resource = Project | Issue | UserProfile;

export interface Project {
    id: string;
    title: string;
    completed: boolean;
    assignees: number;
}

export interface Issue {
    id: string;
    title: string;
    status: string;
    estimate: number;
}

export interface UserProfile {
    id?: string;
    user: User;
    experience: number;
    remote: boolean;
    role: string;
}

export interface User {
    name: string;
    location: string;
    age: number;
}
