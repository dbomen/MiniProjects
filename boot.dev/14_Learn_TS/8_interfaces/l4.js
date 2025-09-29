"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.getHighPriorityEvents = getHighPriorityEvents;
function getHighPriorityEvents(events) {
    var newEvents = [];
    for (var _i = 0, events_1 = events; _i < events_1.length; _i++) {
        var event_1 = events_1[_i];
        if (event_1.severity == "high" ||
            event_1.severity == "critical") {
            newEvents.push(event_1);
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
    },
    {
        type: "error",
        timestamp: 1058510394,
        payload: "Auth failure",
        affectedService: "auth",
        severity: "high",
        code: 401,
    },
    {
        type: "outage",
        timestamp: 1291954900,
        payload: {},
        affectedService: "billing",
        severity: "critical",
        durationSeconds: 600,
    }
]));
