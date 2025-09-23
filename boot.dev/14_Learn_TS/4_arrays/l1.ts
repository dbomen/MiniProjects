export function averageScore(ratings: number[]) {

    if (ratings.length == 0) return 0;

    let sum: number = 0;
    for (let rating of ratings) sum += rating;

    return sum / ratings.length;
}

// TEST
console.log(averageScore([7, 11, 42]) + " == 20");
console.log(averageScore([17, 76, 17, 87, 18, 61]) + " == 46");
