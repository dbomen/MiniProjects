export interface CanReply {
    reply(text: string): string;
}

export interface CanSummarize {
    summarize(conversation: string[]): string;
}

export interface CanAct {
    takeAction(action: string): void;
}

// don't touch above this line

export interface SmartReplyBot extends CanAct, CanReply, CanSummarize {
    name: string;
    status: "online" | "offline" | "starting";
    shutdown: () => string;
}

// TEST
console.log((
    {
        name: "EchoBot",
        status: "online",
        reply: (text) => `Echo: ${text}`,
        summarize: (lines) => `Summary: ${lines.join(" / ")}`,
        takeAction: (action) => console.log(`Action: ${action}`),
        shutdown: () => "Shutting down EchoBot...",
    } as SmartReplyBot).shutdown());
