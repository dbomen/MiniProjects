function getPrimaryAndBackupMessages(messages) {

    if (messages.length == 0) return { primary: undefined, backups: [], };

    const [primary, ...backups] = messages;
    return { primary, backups };
}

// TEST
console.log(getPrimaryAndBackupMessages(
    [
        "Welcome to Textio!",
        "Your order has shipped",
        "Reminder: Payment due soon",
    ]));

export { getPrimaryAndBackupMessages };
