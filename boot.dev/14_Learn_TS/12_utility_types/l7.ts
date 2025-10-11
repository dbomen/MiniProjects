export interface User {
    id: string;
    name: string;
    email: string;
    age: number;
}

export type UserWithoutID = Omit<User, "id">;

export function stripID(user: User): UserWithoutID {
    const { name, email, age } = user;
    return { name, email, age };
}
