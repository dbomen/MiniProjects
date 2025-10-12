export type Blank<T> = {
    [K in keyof T]: null
};

export function resetForm<T>(form: T): Blank<T> {
    const result: Blank<T> = {} as Blank<T>;

    for (const key in form) {
        result[key] = null;
    }

    return result;
}

// TEST
type PermitForm = {
    applicant: string;
    licenseType: "farming" | "fishing" | "mining";
    yearsRequested: number;
};

console.log(resetForm(
    {
        applicant: "Abigail",
        licenseType: "mining",
        yearsRequested: 2,
    } as PermitForm
));
