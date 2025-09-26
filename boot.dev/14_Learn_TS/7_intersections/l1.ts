export type SupportBot = {
    id: string;
    name: string;
    status: string;
    language: string;
};

// don't touch above this line

export type TextBot = SupportBot & {
    messageLog: string[];
    sendMessage: (message: string) => string;
}

export type VoiceBot = SupportBot & {
    callLog: string[];
    phoneNumber: string;
    dialNumber: (phoneNumber: string) => string;
}
