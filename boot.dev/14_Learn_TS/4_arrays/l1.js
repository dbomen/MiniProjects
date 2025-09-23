"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.averageScore = averageScore;
function averageScore(ratings) {
    if (ratings.length == 0)
        return 0;
    var sum = 0;
    for (var _i = 0, ratings_1 = ratings; _i < ratings_1.length; _i++) {
        var rating = ratings_1[_i];
        sum += rating;
    }
    return sum / ratings.length;
}
// TEST
console.log(averageScore([7, 11, 42]) + " == 20");
console.log(averageScore([17, 76, 17, 87, 18, 61]) + " == 46");
