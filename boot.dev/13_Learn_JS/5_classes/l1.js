class Message {

    recipient;
    sender;
    body;

    constructor(recipient, sender, body) {
        this.recipient = recipient;
        this.sender = sender;
        this.body = body;
    }
}

const message = new Message("555-1234", "555-5678", "Live long and prosper");
console.log(message);

// don't touch below this line

export { Message };
