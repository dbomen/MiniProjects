export type LogLevel = "info" | "warn" | "error";
export type LogSourceType = "api" | "database" | "auth";

export type LogMessage = `${LogLevel}: ${string}`;
export type LogSource = `${LogSourceType}_${number}`;

// ?

export function createLogEntry(message: LogMessage, source: LogSource): string {
    return `[${source}] LOG - ${message}`;
}

// TEST
console.log(createLogEntry("info: User logged in" as LogMessage,
    "api_123" as LogSource) + " == [api_123] LOG - info: User logged in");
console.log(createLogEntry("warn: Database connection unstable" as LogMessage,
    "database_456" as LogSource) + " == [database_456] LOG - warn: Database connection unstable");
