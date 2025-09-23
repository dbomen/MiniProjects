export type Mail = {
    from: string;
    to: string[];
    subject: string;
    body: string;
    urgent: boolean;
    cc?: string[];
};

// don't touch below this line

export function processMail(mail: Mail) {
    return `FROM: ${mail.from}
TO: ${mail.to}${!mail.cc ? "" : "\nCC: " + mail.cc}
SUBJECT: ${mail.urgent ? "[URGENT] " : ""}${mail.subject}
BODY: ${mail.body}`;
}

// TEST
console.log(processMail(
    {
        from: "winston@support.ai",
        to: ["captainfalcon@example.com", "laughinglast@example.com"],
        subject: "The Gathering",
        body: "The magical nexus has begun",
        urgent: true,
    } as Mail));
