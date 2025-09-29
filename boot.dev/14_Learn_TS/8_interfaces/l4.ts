export interface SystemEvent {
    type: string;
    timestamp: number;
    payload: string | object;
    affectedService: string;
    severity: "low" | "medium" | "high" | "critical";
}

// don't touch above this line

export interface ErrorEvent extends SystemEvent {
    type: "error";
    payload: string;
    code: number;
}

export interface OutageEvent extends SystemEvent {
    type: "outage";
    severity: "critical";
    durationSeconds: number;
}

export function getHighPriorityEvents(events: SystemEvent[]) {

    let newEvents: SystemEvent[] = [];
    for (let event of events) {
        if (event.severity == "high" ||
            event.severity == "critical") {
            newEvents.push(event);
        }
    }

    return newEvents;
}

// TEST
console.log(getHighPriorityEvents([
    {
        type: "error",
        timestamp: 1291954900,
        payload: "Minor timeout",
        affectedService: "chat",
        severity: "low",
        code: 408,
    } as ErrorEvent,
    {
        type: "error",
        timestamp: 1058510394,
        payload: "Auth failure",
        affectedService: "auth",
        severity: "high",
        code: 401,
    } as ErrorEvent,
    {
        type: "outage",
        timestamp: 1291954900,
        payload: {},
        affectedService: "billing",
        severity: "critical",
        durationSeconds: 600,
    } as OutageEvent
]));
