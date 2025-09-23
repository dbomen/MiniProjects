// don't touch below this line
export function processMail(mail) {
    return `FROM: ${mail.from}
TO: ${mail.to}${!mail.cc ? "" : "\nCC: " + mail.cc}
SUBJECT: ${mail.urgent ? "[URGENT] " : ""}${mail.subject}
BODY: ${mail.body}`;
}
// TEST
console.log(processMail({
    from: "winston@support.ai",
    to: ["captainfalcon@example.com", "laughinglast@example.com"],
    subject: "The Gathering",
    body: "The magical nexus has begun",
    urgent: true,
}));
