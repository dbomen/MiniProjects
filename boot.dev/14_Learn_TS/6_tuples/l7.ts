export function tokenize(input: string): [number, ...string[]] {

    const tokens: Array<string> = input.split(" ");
    const cost: number = tokens.length / 100;

    return [cost, ...tokens]
}

// TEST
console.log(tokenize("Dude! You got a tattoo!"));
console.log(tokenize("So do you, dude!"));
