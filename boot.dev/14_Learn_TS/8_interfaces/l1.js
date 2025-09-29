"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.getSessionDuration = getSessionDuration;
function getSessionDuration(session) {
    return session.endedAt - session.startedAt;
}
// TEST
console.log(getSessionDuration({
    id: "sess_001",
    startedAt: 1700000000,
    endedAt: 1700000450,
    feedback: {
        rating: 5,
        comment: "Boots cast a spell on me!",
    },
}) + " == 450");
console.log(getSessionDuration({
    id: "sess_002",
    startedAt: 1697563200,
    endedAt: 1697564100,
    feedback: {
        rating: 3,
        comment: "Okay experience.",
    },
}) + " == 900");
