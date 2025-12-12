import { createInterface, type Interface } from "readline";
import { getCommands } from "./commands.js";
import { PokeAPI, Pokemon } from "./pokeapi.js";

export type CLICommand = {
    name: string;
    description: string;
    callback: (state: State, ...args: string[]) => Promise<void>
};

export type State = {
    interface: Interface,
    commands: Record<string, CLICommand>,
    pokeApi: PokeAPI
    nextLocationsURL: string,
    prevLocationsURL: string,
    pokedex: Record<string, Pokemon>,
};

export function initState() {
    let rl = createInterface(process.stdin, process.stdout);
    rl.setPrompt("Pokedex > ");
    rl.prompt();

    return {
        interface: rl,
        commands: getCommands(),
        pokeApi: new PokeAPI(),
        nextLocationsURL: "",
        prevLocationsURL: "",
        pokedex: {}
    }
}
