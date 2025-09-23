export type Mail = {
    from: string;
    to: string[];
    cc: string[];
    subject: string;
    body: string;
    urgent: boolean;
};

// don't touch below this line

export function processMail(mail: Mail) {
    return `FROM: ${mail.from}
TO: ${mail.to.join(", ")}
CC: ${mail.cc.join(",")}
SUBJECT: ${mail.urgent ? "[URGENT] " : ""}${mail.subject}
BODY: ${mail.body}`;
}

// TEST
console.log(processMail(
    {
        from: "allan@support.ai",
        to: ["pear2pear@example.com", "baker@example.com"],
        subject: "Salmon delivery",
        body: "We need more salmon or else",
        urgent: true,
        cc: ["ballan@support.ai", "boots@support.ai"],
    } as Mail));
