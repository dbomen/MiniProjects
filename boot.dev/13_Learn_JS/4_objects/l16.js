function mergeTemplates(defaultTemplates, customTemplates) {
    return { ...defaultTemplates, ...customTemplates };
}

const dt = {
    welcome: "Welcome to Textio! Start sending messages today.",
    support: "Need help? Contact our support team.",
};
const ct = {
    promo: "Limited time offer! Sign up now.",
    support: "Reach out to us for exclusive deals.",
};
const expected = {
    welcome: "Welcome to Textio! Start sending messages today.",
    support: "Reach out to us for exclusive deals.",
    promo: "Limited time offer! Sign up now.",
};

console.log(JSON.stringify(mergeTemplates(dt, ct)) === JSON.stringify(expected) ? "PASSED" : "FAILED");

// don't touch below this line

export { mergeTemplates };
