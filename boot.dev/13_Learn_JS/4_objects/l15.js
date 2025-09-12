const campaign = {
    name: "Welcome Campaign",
    maxMessages: 100,
    sentMessages: 30,
    sendMessage: function() {
        this.sentMessages++;
    },
};

console.log(campaign.sentMessages);
campaign.sendMessage();
console.log(campaign.sentMessages);

// don't touch below this line

export { campaign };
