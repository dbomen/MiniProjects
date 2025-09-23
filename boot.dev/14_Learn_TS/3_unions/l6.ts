export type EmploymentStatus = "employed" | "unemployed" | "student" | string;

// don't touch below this line

export function updateEmploymentStatus(status: EmploymentStatus) {
    return `Employment status updated: ${status}`;
}
