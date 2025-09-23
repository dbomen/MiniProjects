export type Mail = {
    from: string;
    to: string[];
    subject: string;
    body: string;
    urgent: boolean;
}

export function processMail(mail: Mail) {
    return `FROM: ${mail.from}
TO: ${mail.to}
SUBJECT: ${mail.urgent ? "[URGENT] " : ""}${mail.subject}
BODY: ${mail.body}`;
}

// TEST
console.log(processMail(
    {
        from: "dan@support.ai",
        to: ["rock@example.com", "star@example.com"],
        subject: "Re: vim",
        body: "Enable vim mode in settings",
        urgent: false,
    } as Mail));
