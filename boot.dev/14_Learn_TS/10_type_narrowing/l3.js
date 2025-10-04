"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.respondToSentiment = respondToSentiment;
function respondToSentiment(sentiment) {
    if (sentiment == "happy" || sentiment == "satisfied")
        return handlePositiveSentiment(sentiment);
    else if (sentiment == "dissatisfied" || sentiment == "angry")
        return handleNegativeSentiment(sentiment);
    else
        return {
            message: "We don't understand.",
            notify: true,
        };
}
function handlePositiveSentiment(sentiment) {
    if (sentiment == "happy")
        return {
            message: "Hooray!",
            notify: false,
        };
    else
        return {
            message: "We are glad.",
            notify: false,
        };
}
function handleNegativeSentiment(sentiment) {
    if (sentiment == "dissatisfied")
        return {
            message: "We are sorry.",
            notify: false,
        };
    else
        return {
            message: "We apologize. A manager will contact you.",
            notify: true,
        };
}
// TEST
console.log(respondToSentiment("happy"));
console.log(respondToSentiment("satisfied"));
console.log(respondToSentiment("dissatisfied"));
console.log(respondToSentiment("angry"));
