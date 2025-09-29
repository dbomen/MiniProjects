export interface UserFeedback {
    rating: number;
    comment: string;
}

export interface SupportSession {
    id: string;
    startedAt: number;
    endedAt: number;
    feedback: UserFeedback;
}

export function getSessionDuration(session: SupportSession) {
    return session.endedAt - session.startedAt;
}

// TEST
console.log(getSessionDuration(
    {
        id: "sess_001",
        startedAt: 1700000000,
        endedAt: 1700000450,
        feedback: {
            rating: 5,
            comment: "Boots cast a spell on me!",
        },
    } as SupportSession) + " == 450");

console.log(getSessionDuration(
    {
        id: "sess_002",
        startedAt: 1697563200,
        endedAt: 1697564100,
        feedback: {
            rating: 3,
            comment: "Okay experience.",
        },
    } as SupportSession) + " == 900");
