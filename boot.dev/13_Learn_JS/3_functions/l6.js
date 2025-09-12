function reformat(message, formatter) {

    return "TEXTIO: " + formatter(formatter(formatter(message)));
}

// don't touch below this line

export { reformat };
