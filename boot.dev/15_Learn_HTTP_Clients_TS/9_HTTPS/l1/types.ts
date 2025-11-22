export interface UserProfile {
    id: string;
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
