class Sender {
    constructor(recipient) {
        this.recipient = recipient;
    }

    sendMessage(message) {
        throw new Error("sendMessage method must be implemented by subclasses");
    }
}

// don't touch above this line

class SMSSender extends Sender {

    sendMessage(message) {
        console.log(`Sending SMS to ${this.recipient}: ${message}`);
    }
}

class EmailSender extends Sender {

    sendMessage(message) {
        console.log(`Sending email to ${this.recipient}: ${message}`);
    }
}

const sender = new Sender("555-1234");
// sender.sendMessage();
const smsSender = new SMSSender("555-1234");
smsSender.sendMessage("message from sms");
const emailSender = new EmailSender("555-1234");
emailSender.sendMessage("message from email :)");

// don't touch below this line

export { Sender, SMSSender, EmailSender };
