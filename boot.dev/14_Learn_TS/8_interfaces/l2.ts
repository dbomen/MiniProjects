export interface Message {
    id: string;
    sender: string;
    recipient: string;
    timestamp: number;
}

// don't touch above this line

interface TextMessage extends Message {
    text: string;
    carrier: string;
}

interface EmailMessage extends Message {
    subject: string;
    body: string;
}

export function getEmailDescription(email: EmailMessage): string {
    return `[EMAIL] ${email.subject}: ${email.body}`;
}

export function getTextMessageDescription(text: TextMessage): string {
    return `[TEXT] ${text.text}` + ` - Sent via ${text.carrier}`;
}
