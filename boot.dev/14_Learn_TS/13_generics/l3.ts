interface HasEmail {
    email: string
}

export function pluckEmails<T extends HasEmail>(arr: T[]) {
    let out: string[] = [];
    for (let el of arr) {
        out.push(el.email);
    }
    return out;
}
