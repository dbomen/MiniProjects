"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.getStatusMessage = getStatusMessage;
var waitingMessage = "Awaiting prompt";
var thinkingMessage = "Cooking";
var respondingMessage = "Sending response";
function getStatusMessage(status) {
    var map = {
        waiting: waitingMessage,
        thinking: thinkingMessage,
        responding: respondingMessage,
    };
    return map[status];
}
