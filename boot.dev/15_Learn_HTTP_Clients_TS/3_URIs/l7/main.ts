function getMailtoLinkForEmail(email: string): string {
    return `mailto:${email}`
}

// TEST
console.log(getMailtoLinkForEmail("wayne.lagner@dev.boot") + " == " + "mailto:wayne.lagner@dev.boot");

// don't touch below this line

export { getMailtoLinkForEmail };
