"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.countComplaints = countComplaints;
function countComplaints(chats) {
    var counts = { questions: 0, complaints: 0, upgrades: 0, refunds: 0 };
    for (var _i = 0, chats_1 = chats; _i < chats_1.length; _i++) {
        var chat = chats_1[_i];
        counts = incrementCount(chat, counts);
    }
    return counts;
}
function incrementCount(chat, counts) {
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
