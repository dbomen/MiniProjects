export type Topic = "question" | "complaint" | "upgrade" | "refund";

type Chat = {
    topic: Topic;
    userId: string;
};

type CountReport = {
    questions: number;
    complaints: number;
    upgrades: number;
    refunds: number;
};

export function countComplaints(chats: Chat[]): CountReport {
    let counts = { questions: 0, complaints: 0, upgrades: 0, refunds: 0 };
    for (const chat of chats) {
        counts = incrementCount(chat, counts);
    }
    return counts;
}

function incrementCount(chat: Chat, counts: CountReport): CountReport {
    switch (chat.topic) {
        case "question":
            counts.questions++;
            return counts;
        case "complaint":
            counts.complaints++;
            return counts;
        case "refund":
            counts.refunds++;
            return counts;
        case "upgrade":
            counts.upgrades++;
            return counts;
    }
}
