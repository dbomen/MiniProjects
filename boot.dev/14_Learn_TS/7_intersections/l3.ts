export type SentimentTag = {
    type: "positive" | "neutral" | "negative";
    score: number;
    flagged: boolean;
};

export type ChannelTag = {
    type: "chat" | "email" | "phone";
    receivedAt: string;
    verified: boolean;
};

export type ReviewMethod = "manual_review" | "auto_process";

// don't touch above this line

export type TicketMetadata = {
    sentiment: SentimentTag;
    channel: ChannelTag;
}

export function getReviewMethod(metadata: TicketMetadata): ReviewMethod {
    const needsReview = metadata.sentiment.flagged || !metadata.channel.verified || metadata.channel.type === "phone";

    return needsReview ? "manual_review" : "auto_process";
}

// TEST
console.log(getReviewMethod(
    {
        sentiment: { type: "positive", score: 0.9, flagged: false },
        channel: {
            type: "chat",
            verified: true,
            receivedAt: "2025-03-24T12:00:00Z",
        },
    } as TicketMetadata) + " == auto_process");

console.log(getReviewMethod(
    {
        sentiment: { type: "negative", score: 0.3, flagged: true },
        channel: {
            type: "email",
            verified: true,
            receivedAt: "2025-03-24T12:00:00Z",
        },
    } as TicketMetadata) + " == manual_review");
