export type SupportRequest = {
    id: string;
    severity: RequestSeverity;
    description: string;
};

// don't touch above this line

export enum RequestSeverity {
    Low,
    Medium,
    High,
    Critical,
}

export function isCritical(request: SupportRequest) {
    return (request.severity === RequestSeverity.Critical);
}

// TEST
console.log(isCritical(
    {
        id: "t-331",
        severity: RequestSeverity.Low,
        description: "Minor UI glitch",
    } as SupportRequest) + " == false");

console.log(isCritical(
    {
        id: "t-102",
        severity: RequestSeverity.Medium,
        description: "Slow response times",
    } as SupportRequest) + " == false");

console.log(isCritical(
    {
        id: "t-004",
        severity: RequestSeverity.Critical,
        description: "Bear in the server room",
    } as SupportRequest) + " == true");
