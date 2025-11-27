interface JelloIssue {
    id: string;
    title: string;
}

export function validateIssue(data: any): JelloIssue {
    if (!data || typeof data !== "object") throw Error();
    if (typeof data.id !== "string") throw Error();
    if (typeof data.title !== "string") throw Error();
    return data as JelloIssue;
}
