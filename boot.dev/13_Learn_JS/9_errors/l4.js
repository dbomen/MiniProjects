const sendMessage = (msg) => {

    if (msg.length > 70) throw new Error("Message is too long");
    return msg;
};

// TEST
sendMessage("Inconceivable!");
sendMessage("You keep using that word. I do not think it means what you think it means.");

export { sendMessage };
