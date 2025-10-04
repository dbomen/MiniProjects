export type UserFeedback = {
    email?: string;
    rating?: number;
};

export function handleFeedback(feedback: UserFeedback) {
    if (feedback.rating === undefined || !isValidRating(feedback.rating)) return "Give a rating between 1 and 5.";
    else if (feedback.email === undefined || !feedback.email.includes("@")) return "Provide a valid email address";

    return `Thanks, ${getEmailUsername(feedback.email)}! Rating: ${ratingToString(feedback.rating)}`;
}

function getEmailUsername(email: string): string {
    const atIndex = email.indexOf("@");
    return atIndex !== -1 ? email.slice(0, atIndex) : email;
}

function isValidRating(rating: number): rating is 1 | 2 | 3 | 4 | 5 {
    return (
        rating === 1 || rating === 2 || rating === 3 || rating === 4 || rating === 5
    );
}

function ratingToString(rating: 1 | 2 | 3 | 4 | 5): string {
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
