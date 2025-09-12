class Sender {
    constructor(recipient) {
        this.recipient = recipient;
    }

    formatMessage(message) {
        return `To: ${this.recipient}, Message: ${message}`;
    }
}

// don't touch above this line

class SMSSender extends Sender {

    formatMessage(message) {
        return `${super.formatMessage(message)} [SMS]`;
    }
}

class EmailSender extends Sender {

    formatMessage(message) {
        return `${super.formatMessage(message)} [EMAIL]`;
    }
}

const smsSender = new SMSSender("555-1234");
console.log(smsSender.formatMessage("Hello via SMS"));

const emailSender = new EmailSender("555-1234");
console.log(emailSender.formatMessage("Hello via Email"));

// don't touch below this line

export { Sender, SMSSender, EmailSender };
