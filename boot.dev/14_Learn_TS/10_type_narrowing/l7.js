"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.handleFeedback = handleFeedback;
function handleFeedback(feedback) {
    if (feedback.rating === undefined || !isValidRating(feedback.rating))
        return "Give a rating between 1 and 5.";
    else if (feedback.email === undefined || !feedback.email.includes("@"))
        return "Provide a valid email address";
    return "Thanks, ".concat(getEmailUsername(feedback.email), "! Rating: ").concat(ratingToString(feedback.rating));
}
function getEmailUsername(email) {
    var atIndex = email.indexOf("@");
    return atIndex !== -1 ? email.slice(0, atIndex) : email;
}
function isValidRating(rating) {
    return (rating === 1 || rating === 2 || rating === 3 || rating === 4 || rating === 5);
}
function ratingToString(rating) {
    switch (rating) {
        case 1:
            return "Very Bad";
        case 2:
            return "Bad";
        case 3:
            return "Average";
        case 4:
            return "Good";
        case 5:
            return "Excellent";
    }
}
