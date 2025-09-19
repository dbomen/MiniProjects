export function logSystemEvent(event: string,
    severity: "info" | "warning" | "error"
): string {
    return `SYSTEM ${severity.toUpperCase()}: ${event}`;
}

console.log(logSystemEvent("EVENT 1", "error"));
