class Message {

    static numberOfMessages = 0;
    static fullLength = 0;

    constructor(recipient, sender, body) {
        this.recipient = recipient;
        this.sender = sender;
        this.body = body;

        Message.numberOfMessages++;
        Message.fullLength += body.length;
    }

    static getAverageMessageLength() {

        const average = Message.fullLength / Message.numberOfMessages
        return Math.round(average * 100) / 100;
    }
}

// don't touch below this line

export { Message };
