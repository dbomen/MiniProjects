type PositiveSentiment = "happy" | "satisfied";
type NegativeSentiment = "dissatisfied" | "angry";

export type Sentiment = PositiveSentiment | NegativeSentiment;
export type Response = { message: string; notify: boolean };

export function respondToSentiment(sentiment: Sentiment): Response {
    if (sentiment == "happy" || sentiment == "satisfied") return handlePositiveSentiment(sentiment);
    else if (sentiment == "dissatisfied" || sentiment == "angry") return handleNegativeSentiment(sentiment);
    else return {
        message: "We don't understand.",
        notify: true,
    } as Response;
}

function handlePositiveSentiment(sentiment: PositiveSentiment): Response {
    if (sentiment == "happy") return {
        message: "Hooray!",
        notify: false,
    } as Response;
    else return {
        message: "We are glad.",
        notify: false,
    } as Response;
}

function handleNegativeSentiment(sentiment: NegativeSentiment): Response {
    if (sentiment == "dissatisfied") return {
        message: "We are sorry.",
        notify: false,
    } as Response;
    else return {
        message: "We apologize. A manager will contact you.",
        notify: true,
    } as Response;
}

// TEST
console.log(respondToSentiment("happy"));
console.log(respondToSentiment("satisfied"));
console.log(respondToSentiment("dissatisfied"));
console.log(respondToSentiment("angry"));
