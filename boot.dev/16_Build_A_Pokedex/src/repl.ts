import { State } from "./state.js";

export function cleanInput(input: String): String[] {
    return input.split(" ").map(word => word.toLowerCase()).filter(word => word.length > 0);
}

export function startREPL(state: State) {

    // start reading lines
    state.interface.on("line", async input => {
        let inputSanitized = cleanInput(input);
        let command = inputSanitized[0];

        if (state.commands[`${command}`]) {
            try {
                await state.commands[`${command}`].callback(state, ...inputSanitized.map(s => s.toString()));
            } catch (error) {
                console.log(error);
            }
        }
        else {
            console.log("what?");
        }
        state.interface.prompt();
    });
}
