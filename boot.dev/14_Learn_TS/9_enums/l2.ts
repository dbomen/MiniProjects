export type SupportRequest = {
    id: string;
    severity: RequestSeverity;
    description: string;
};

// don't touch above this line

export enum RequestSeverity {
    Low = "LOW",
    Medium = "MEDIUM",
    High = "HIGH",
    Critical = "CRITICAL",
};

export function convertSeverityToLabel(severity: number) {
    switch (severity) {
        case 0: return "LOW"
        case 1: return "MEDIUM"
        case 2: return "HIGH"
        case 3: return "CRITICAL"
    }
}

// TEST
console.log(convertSeverityToLabel(0) + " == LOW");
console.log(convertSeverityToLabel(3) + " == CRITICAL");
