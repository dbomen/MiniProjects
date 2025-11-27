import readline from "node:readline"
import { getCommands } from "./commands.js";

export function cleanInput(input: String): String[] {
    return input.split(" ").map(word => word.toLowerCase()).filter(word => word.length > 0);
}

export function startREPL() {
    let rl = readline.createInterface(process.stdin, process.stdout);
    rl.setPrompt("Pokedex > ");
    rl.prompt();
    let commands = getCommands()
    rl.on("line", input => {
        let inputSanitized = cleanInput(input);
        let command = inputSanitized[0];

        if (commands[`${command}`]) {
            commands[`${command}`].callback(commands);
        }
        else {
            console.log("what?");
        }
        rl.prompt();
    });
}
