export type ModelStatus = "waiting" | "thinking" | "responding";

const waitingMessage = "Awaiting prompt";
const thinkingMessage = "Cooking";
const respondingMessage = "Sending response";

export function getStatusMessage(status: ModelStatus) {
    const map: Record<ModelStatus, string> = {
        waiting: waitingMessage,
        thinking: thinkingMessage,
        responding: respondingMessage,
    };

    return map[status];
}
