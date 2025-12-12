import { CLICommand, State } from "./state.js";

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
        map: {
            name: "mapb",
            description: "print next 20 location areas",
            callback: commandMap,
        },
        mapb: {
            name: "mapb",
            description: "print prev 20 location areas",
            callback: commandMapb,
        },
        explore: {
            name: "explore",
            description: "explore area for pokemon",
            callback: commandExplore,
        },
        catch: {
            name: "catch",
            description: "try to catch a pokemon",
            callback: commandCatch,
        },
        inspect: {
            name: "inspect",
            description: "inspect caught pokemono",
            callback: commandInspect,
        }
        // ...
    };
}

// FUNCTIONS
// =================================================

export async function commandExit(state: State) {
    console.log(`Closing the Pokedex... Goodbye!`);
    state.interface.close();
    process.exit(0);
}

export async function commandHelp(state: State) {
    console.log(`
        Welcome to the Pokedex!
        Usage:
    `);
    for (const key in state.commands) {
        console.log(`${state.commands[key].name}: ${state.commands[key].description}`);
    }
}

export async function commandMap(state: State) {
    console.log(`Fetching next 20 location ares...\n`);
    let locations;
    if (!state.nextLocationsURL)
        locations = await state.pokeApi.fetchLocations();
    else
        locations = await state.pokeApi.fetchLocations(state.nextLocationsURL);

    state.nextLocationsURL = locations.next;
    state.prevLocationsURL = locations.previous;
    locations.results.forEach((location) => {
        console.log(location.name);
    });
    console.log();
}

export async function commandMapb(state: State) {
    console.log(`Fetching previous 20 location ares...\n`);
    let locations;
    if (!state.prevLocationsURL)
        locations = await state.pokeApi.fetchLocations();
    else
        locations = await state.pokeApi.fetchLocations(state.prevLocationsURL);

    state.nextLocationsURL = locations.next;
    state.prevLocationsURL = locations.previous;
    locations.results.forEach((location) => {
        console.log(location.name);
    });
    console.log();
}

export async function commandExplore(state: State, arg0: string, arg1: string) {
    console.log(`Fetching previous location area for pokemon...\n`);

    let location = await state.pokeApi.fetchLocation(arg1);

    location.pokemon_encounters.forEach((pokemon) => {
        console.log(pokemon.pokemon.name);
    });
    console.log();
}

export async function commandCatch(state: State, arg0: string, arg1: string) {
    console.log(`Throwing a Pokeball at ${arg1}...\n`);

    let pokemon = await state.pokeApi.fetchPokemon(arg1);
    let catch_attempt = Math.random() * 700;
    if (catch_attempt >= pokemon.base_experience) {
        console.log(`cought ${arg1}...\n`);
        state.pokedex[arg1] = pokemon;
    }
    else {
        console.log(`missed...\n`);
    }
}

export async function commandInspect(state: State, arg0: string, arg1: string) {
    let pokemon = state.pokedex[arg1];
    if (pokemon)
        console.log(pokemon);
    else
        console.log(`You have not cought ${arg1} yet...`);
}
