"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.getReviewMethod = getReviewMethod;
function getReviewMethod(metadata) {
    var needsReview = metadata.sentiment.flagged || !metadata.channel.verified || metadata.channel.type === "phone";
    return needsReview ? "manual_review" : "auto_process";
}
// TEST
console.log(getReviewMethod({
    sentiment: { type: "positive", score: 0.9, flagged: false },
    channel: {
        type: "chat",
        verified: true,
        receivedAt: "2025-03-24T12:00:00Z",
    },
}) + " == auto_process");
console.log(getReviewMethod({
    sentiment: { type: "negative", score: 0.3, flagged: true },
    channel: {
        type: "email",
        verified: true,
        receivedAt: "2025-03-24T12:00:00Z",
    },
}) + " == manual_review");
