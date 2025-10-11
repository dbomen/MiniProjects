export interface User {
    id: string;
    email: string;
}

export function updateUser(user: Partial<User>) {
    if (user.id) {
        return "can't update id";
    }
    if (user.email) {
        return `updating email to ${user.email}`;
    }
    return "nothing to update";
}
