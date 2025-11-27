export type CLICommand = {
    name: string;
    description: string;
    callback: (commands: Record<string, CLICommand>) => void;
};

export function getCommands(): Record<string, CLICommand> {
    return {
        exit: {
            name: "exit",
            description: "Exits the pokedex",
            callback: commandExit,
        },
        help: {
            name: "help",
            description: "Prints help",
            callback: commandHelp,
        },
        // ...
    };
}

// FUNCTIONS
// =================================================

export function commandExit() {
    console.log(`Closing the Pokedex... Goodbye!`);
    process.exit(0);
}

export function commandHelp() {
    console.log(`
        Welcome to the Pokedex!
        Usage:

        help: Displays a help message
        exit: Exit the Pokedex`
    );
}
