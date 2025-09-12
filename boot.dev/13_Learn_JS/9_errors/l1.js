function createError(message) {

    return new Error("Error: " + message);
}

// TEST
console.log(createError("Connection to Textio lost!").message);

// don't touch below this line

export { createError };
