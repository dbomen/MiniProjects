export function interpolateComment(
    id: number,
    comment: string,
    comments: (string | number)[],
) {
    for (let i = 0; i < comments.length; i++) {

        let _id = comments[i];
        if (typeof _id === "number" && _id === id) comments[i] = comment;
    }
}

// TEST
let arr = ["salmon discount code", 418, "I can't remember my email", 420];
interpolateComment(418, "Refresh token is missing", arr);
console.log(arr);

arr = ["add scarlett johansson voice option", "it's not its", 42];
interpolateComment(42, "will gippity be my gf?", arr);
console.log(arr);
